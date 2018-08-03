package com.ths.lxf.springboot.controller;

import com.ths.lxf.springboot.common.MyException;
import com.ths.lxf.springboot.common.RestModel;
import com.ths.lxf.springboot.service.TestService;
import com.ths.lxf.springboot.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping("/test")
public class TestController {

    private static Logger logger = LoggerFactory.getLogger(TestController.class);

    @Resource
    private TestService testService;

    @RequestMapping(value = "/getAge", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public RestModel getOk(int age) {
        RestModel restModel = new RestModel();
        try {
            if (StringUtil.isNull(age)) {
                throw new MyException("age不能为空");
            }
            restModel.setData(testService.getAge(age));
            restModel.setMsgCode(0);
            logger.info("查询成功：{}",age);
            return restModel;
        } catch (MyException e) {
            restModel.setMsgCode(1);
            restModel.setMsg("查看失败：" + e.getMessage());
            logger.error("查看失败", e);
        } catch (Exception e) {
            restModel.setMsgCode(1);
            restModel.setMsg("查看失败：" + e.getMessage());
            logger.error("查看失败", e);
        }
        return restModel;
    }
}
