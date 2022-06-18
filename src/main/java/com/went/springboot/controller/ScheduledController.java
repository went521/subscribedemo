package com.went.springboot.controller;

import com.went.springboot.mapper.SubscribeMapper;
import com.went.springboot.pojo.Subscribe;
import com.went.springboot.pojo.SubscribeExample;
import lombok.Data;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author went
 * @version 1.0
 * @date 2022/5/30 9:56
 */
@Controller

public class ScheduledController {

    @Autowired
    private SubscribeMapper subscribeMapper;

    //  "0 0 0 * * ? "每日0点执行一次这个方法 删除过期的预约
    @Scheduled(cron = "0 0 0 * * ? ")
    public void task1(){
        Subscribe subscribe = new Subscribe();
       // String openId = (String) session.getAttribute("openId");
        subscribe.setUserOpenid("openId123");
        SubscribeExample example = new SubscribeExample();
        SubscribeExample.Criteria criteria = example.createCriteria();
        Date date = new Date();
        criteria.andEndTimeLessThanOrEqualTo(date);
        int row = subscribeMapper.deleteByExample(example);
    }
}
