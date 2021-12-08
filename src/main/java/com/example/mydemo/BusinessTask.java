package com.example.mydemo;

import com.example.mydemo.security.DistributedLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
//@RestController
//@RequestMapping("/redis")
public class BusinessTask implements CommandLineRunner {

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

    @Override
//    @Scheduled(cron = "0/10 * * * * ? ")
    public void run(String... strings) throws Exception {

        doSomething();
    }
//        boolean lock = distributedLock.getLock(LOCK_ID, 10 * 1000);
//        if (lock) {
//            System.out.println("执行任务");
////            return "执行任务";
//            distributedLock.releaseLock(LOCK_ID);
//        } else {
//            System.out.println("没有抢到锁");
////            return "没有抢到锁";
//        }
//    }
}