package com.example.mydemo.controller;

import com.example.mydemo.security.DistributedLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
//@RestController
//@RequestMapping("/redis")
public class BusinessTaskController {

    private final static String LOCK_ID = "happyjava";

    @Autowired
    DistributedLock distributedLock;

    @Scheduled(cron = "0/10 * * * * ? ")
//    @GetMapping(value = "test")
    public void doSomething() {
        boolean lock = distributedLock.getLock(LOCK_ID, 10 * 1000);
        if (lock) {
            System.out.println("执行任务");
//            return "执行任务";
            distributedLock.releaseLock(LOCK_ID);
        } else {
            System.out.println("没有抢到锁");
//            return "没有抢到锁";
        }
    }

}