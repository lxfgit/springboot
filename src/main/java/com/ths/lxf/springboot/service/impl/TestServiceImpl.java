package com.ths.lxf.springboot.service.impl;

import com.ths.lxf.springboot.common.MyException;
import com.ths.lxf.springboot.service.TestService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("testService")
public class TestServiceImpl implements TestService {
    @Override
    public Map<String, Object> getAge(int age) {
        Map<String, Object> res = new HashMap<>();
        if (age > 100 || age <= 0) {
            throw new MyException("年龄范围不正确：" + age);
        }
        if (age > 50) {
            res.put("status", " 中年");
            res.put("msg", "年过半百");
        } else {
            res.put("status", " 青年");
            res.put("msg", "正值青年");
        }
        return res;
    }
}
