package com.ringbet.luckdraw.manager;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.ringbet.luckdraw.entity.LuckuserVO;
import com.ringbet.luckdraw.entity.SigninuserVO;
import com.ringbet.luckdraw.model.Awards;
import com.ringbet.luckdraw.model.Luckuser;
import com.ringbet.luckdraw.service.AwardsService;
import com.ringbet.luckdraw.service.LuckuserService;
import com.ringbet.luckdraw.service.SigninuserService;

@Component
public class LuckdrawManager implements InitializingBean, DisposableBean {
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private AwardsService awardsService;
	@Resource
	private SigninuserService signinuserService;
	@Resource
	private LuckuserService luckuserService;
	
	private ScheduledExecutorService executor = Executors.newScheduledThreadPool(3); //.newSingleThreadScheduledExecutor();
	//执行间隔时间，单位秒
	//奖项
	private Map<Integer, Awards> awardsMap = new LinkedHashMap<Integer, Awards>();
	
	private List<Integer> luckNums = new ArrayList<Integer>();
	private List<SigninuserVO> signinuserVOs = new ArrayList<SigninuserVO>();
	private List<LuckuserVO> luckuserVOs = new ArrayList<LuckuserVO>();
	
	/** 开奖状态：0未开始，1暂停，2开始，3完结 */
	private int drawStatus = 0;
	//中奖人数累计
	private int luckcount = 0;
	
	//执行间隔时间，单位秒
	private long awardsInteval = 3;
	//延迟执行时间，单位秒
    private long delay = 10;
    
    
    /** 初始化状态/已经查询成功 */
	private final static int LOG_CODE_0 = 0;
	/** 查询失败，且已经打印  */
    private final static int LOG_CODE_1 = 1;
    /** 查询报错，且已经打印  */
    private final static int LOG_CODE_2 = 2;
    private int awardsLogCode = LOG_CODE_0;
	

	private static LuckdrawManager ldm = null;
    public static LuckdrawManager getInstance()
    {
    	if (null == ldm)
    		ldm = new LuckdrawManager();
        return ldm;
    }
    
    @PostConstruct //通过@PostConstruct实现初始化bean之前进行的操作
    public void init() {
    	ldm = this;
    	ldm.awardsService = this.awardsService;
    	ldm.signinuserService = this.signinuserService;
    	ldm.luckuserService = this.luckuserService;
    	
    }
    
    
    private Runnable runnable = new Runnable() {
		@Override
		public void run() {
			try {
				queryInfo();
			} catch (Exception e) {
				if (LOG_CODE_2 != awardsLogCode) {
					awardsLogCode = LOG_CODE_2;
					log.error(e.getMessage());
				}
			}
		}
	};
	
	private void queryInfo() {
		if (null == awardsMap || 0 == awardsMap.size()) {
			List<Awards> list = awardsService.findAll();
			if (null == list || list.isEmpty()) {
				if (LOG_CODE_1 != awardsLogCode) {
					awardsLogCode = LOG_CODE_1;
					log.info("BulletinResponse is null!");
				}
				return ;
			}
			awardsLogCode = LOG_CODE_0;
			for (Awards a : list) {
				awardsMap.put(a.getId(), a);
			}
	//		executor.shutdown();
		}
		
		List<SigninuserVO> suvos = signinuserService.findLastSigninuserVOs();
		if (null != suvos && !suvos.isEmpty())
			signinuserVOs = suvos;
		
		List<LuckuserVO> luvos = luckuserService.findLastLuckuserVOs();
		if (null != luvos && !luvos.isEmpty())
			luckuserVOs = luvos;
	}
    
	@Override
	public void destroy() throws Exception {
		if(executor!=null){
       	 	executor.shutdown();
        }
	}
	@Override
	public void afterPropertiesSet() throws Exception {
		executor.scheduleAtFixedRate(runnable, delay, awardsInteval, TimeUnit.SECONDS);
	}
    
    
    public boolean startDraw() {
    	if (2 == drawStatus || luckcount == signinuserVOs.size()) //开奖或签到人数已经达到了中奖人数（结束状态）
    		return false;
    		
    	if (0 == drawStatus || 1 == drawStatus) {//未开始或暂停状态
    		//拿到所有签到账号
    		List<SigninuserVO> list = signinuserService.findLastSigninuserVOs();
			if (null == list || list.isEmpty())	//失败
				return false;
			signinuserVOs = list;
			log.info(JSON.toJSONString(signinuserVOs));
			//有多少个账号就有多少个幸运号
			luckNums.clear();
			for (int i=0;i<signinuserVOs.size();i++) {
				luckNums.add(i+1);
			}
			//拿到所有中奖账号
			List<LuckuserVO> luvos = luckuserService.findLastLuckuserVOs();
			log.info(String.format("luckNums[%s], luvos[%s]", JSON.toJSONString(luckNums), JSON.toJSONString(luvos)));
			if (null != luvos && !luvos.isEmpty()) {
				luckuserVOs = luvos;
				//重置获奖人数
				luckcount = 0;
				//把已中奖的号码从彩池剔除
				for (LuckuserVO luvo : luckuserVOs) {
					int index = luckNums.indexOf(luvo.getLucknum());
					if (-1 != index) {
						luckNums.remove(index);
						luckcount++;
					}
				}
			}
			log.info(String.format("最终彩池号码luckNums[%s]", JSON.toJSONString(luckNums)));
			drawStatus = 2;
    	}
    	
    	/*if (1 == drawStatus) {	//暂停状态
			List<SigninuserVO> list = signinuserService.findLastSigninuserVOs();
			if (null == list || list.isEmpty())	//失败
				return false;
			signinuserVOs = list;
			if (signinuserVOs.size() < list.size()) {
				for (int i=signinuserVOs.size()+1; i<=list.size(); i++) {
					luckNums.add(i);
				}
			}
			drawStatus = 2;
		}*/
    	return true;
    }
    
    public boolean pauseDraw() {
    	if (!isStart())
    		return false;
    	drawStatus = 1;
    	return true;
    }
    
    public synchronized LuckuserVO joinDraw(Integer signinuserid) {
    	log.info(String.format("drawStatus=%d, signinuserid=%d, luckNums=[%s]", drawStatus, signinuserid, JSON.toJSONString(luckNums)));
    	if (2 != drawStatus)
    		return null;
    		
    	if (null == signinuserid || signinuserid.equals(0))
    		return null;
    	
    	Random random = new Random();
    	int index = random.nextInt(luckNums.size());
    	int luckNum = luckNums.get(index);
    	
    	Integer awardsid = null;
    	int oldCount = 0;
    	for (Integer key : awardsMap.keySet()) {
    		if (luckNum <= (oldCount + awardsMap.get(key).getCount())) {
    			awardsid = key;
    			break;//找到奖项了，结束吧
    		}
    		oldCount += awardsMap.get(key).getCount();
    	}
    	log.info(String.format("signinuserid=%d, lucknum=%d, awardsid=%d, oldLuckcount=%d", signinuserid, luckNum, awardsid, luckcount));
    	
    	Luckuser lu = new Luckuser();
    	lu.setAwardsid(awardsid);
    	lu.setCreatetime(new Date());
    	lu.setNumber(luckNum);
    	lu.setSigninuserid(signinuserid);
    	luckuserService.save(lu);
    	LuckuserVO luvo = luckuserService.findLuckuserVO(signinuserid);
    	if (null == luvo || null == luvo.getId() || luvo.getId().equals(0))
    		return null;
    	
    	luckNums.remove(index);
    	luckcount++;
    	if (luckcount == signinuserVOs.size()) {
    		drawStatus = 3;
    	} else if (0 == luckcount%10 && 30 > luckcount) {	//前两次开奖，每次只开10个
    		drawStatus = 1;
    	}
    	return luvo;
    }
    
    
    public boolean isFinish() {
    	return (3 == drawStatus);
    }
    
    public boolean isStart() {
    	return (2 == drawStatus);
    }
    
    public boolean isPause() {
    	return (1 == drawStatus);
    }
    
    public boolean isInit() {
    	return (0 == drawStatus);
    }
    
    public List<SigninuserVO> getSigninuserVOs() {
    	return signinuserVOs;
    }
    
    public List<LuckuserVO> getLuckuserVOs() {
    	return luckuserVOs;
    }
    
    
    public static void main(String[] args) throws UnsupportedEncodingException {
//    	List<Integer> list = new ArrayList<Integer>();
//    	list.add(3);
//    	Random random = new Random();
//    	int index = random.nextInt(list.size());
//    	int luckNum = list.get(index);
//    	System.out.println(index + "<>" + luckNum);
    	
    	String str = "徐小胖";
    	System.out.println(new String(str.getBytes("UTF-8"), "GBK"));
	}
}
