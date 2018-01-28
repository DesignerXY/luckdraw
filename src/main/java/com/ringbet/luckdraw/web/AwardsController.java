package com.ringbet.luckdraw.web;
import com.ringbet.luckdraw.core.Result;
import com.ringbet.luckdraw.core.ResultGenerator;
import com.ringbet.luckdraw.model.Awards;
import com.ringbet.luckdraw.service.AwardsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2018/01/24.
*/
@RestController
@RequestMapping("/awards")
public class AwardsController {
    @Resource
    private AwardsService awardsService;

    @RequestMapping("/add")
    public Result add(Awards awards) {
        awardsService.save(awards);
        return ResultGenerator.genSuccessResult();
    }

    @RequestMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        awardsService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @RequestMapping("/update")
    public Result update(Awards awards) {
        awardsService.update(awards);
        return ResultGenerator.genSuccessResult();
    }

    @RequestMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        Awards awards = awardsService.findById(id);
        return ResultGenerator.genSuccessResult(awards);
    }

    @RequestMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Awards> list = awardsService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
