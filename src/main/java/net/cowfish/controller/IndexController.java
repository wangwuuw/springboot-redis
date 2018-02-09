package net.cowfish.controller;

import net.cowfish.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @classDesc： 功能描述：（springboot整合redis）
 * @author：王武
 * @createTime 2018/2/9
 * @verson: v1.0
 * @copyright: 上海江豚教育科技有限公司
 * @qq:834667820
 */
@RestController
public class IndexController {
    @Autowired
    private RedisService redisService;
    @RequestMapping("/setString")
    public String setString(String key,String value){
        redisService.setString(key,value);
        return "success";
    }
    @RequestMapping("/getString")
    public String getString(String key){
        String string = redisService.getString(key);
        return string;
    }
    @RequestMapping("/setList")
    public String setList(String key){
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("123");
        strings.add("456");
        redisService.setList(key,strings);
        return "success";
    }

}
