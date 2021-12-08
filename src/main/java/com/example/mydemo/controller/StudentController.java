package com.example.mydemo.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.mydemo.security.RedisConfig;
import com.example.mydemo.security.RedisNewConfig;
import com.example.mydemo.security.RedisLockTestAnnotation;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yanshao on 2018/11/23.
 */
@RestController
@RequestMapping("/student")
public class StudentController {
    @PostMapping(value="/update")
    @RedisLockTestAnnotation(redisKey = RedisNewConfig.REDIS_TEST + "#0")
    public JSONObject sutdentInfoUpdate(@Param("studentId") String studentId,
                                        @Param("age") int age){
        JSONObject result = new JSONObject();
        result.put("update","success");
        return result;
    }
}
