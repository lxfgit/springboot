package com.ths.lxf.springboot.controller;

import com.ths.lxf.springboot.mail.EmailService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/mail", method = {RequestMethod.GET, RequestMethod.PUT})
public class TestMailController {

    @Resource
    private EmailService emailService;

    @ResponseBody
    @RequestMapping(value = "/sendEmail", method = {RequestMethod.GET, RequestMethod.PUT})
    public void sendEmail() {
        emailService.sendSimpleEmail("229357414@qq.com","this is your email","nihao");
    }

}
