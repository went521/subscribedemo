package com.went.springboot.controller;

import com.went.springboot.mapper.SubscribeMapper;
import com.went.springboot.pojo.Subscribe;
import com.went.springboot.pojo.SubscribeExample;
import com.went.springboot.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author went
 * @version 1.0
 * @date 2022/5/29 22:07
 */
@Controller
public class SubscribeController {

    @Autowired
    private SubscribeMapper subscribeMapper;

    @GetMapping("/room/selectbytime")
    public ArrayList<Integer> choose(
            @RequestParam("startTime") @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date startDate,
            @RequestParam("endTime") @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date endDate) throws ParseException {
        SubscribeExample example = new SubscribeExample();
        SubscribeExample.Criteria criteria = example.createCriteria();
        //String转Date
//        Date startdat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startTime);
//        Date enddate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTime);

        criteria.andStartTimeBetween(startDate,endDate).andEndTimeBetween(startDate,endDate);
        // criteria.andRoomIdEqualTo(roomId);
        List<Subscribe> subscribeList = subscribeMapper.selectByExample(example);
        ArrayList<Integer> roomidList = new ArrayList<>();
        for(Subscribe s :subscribeList){
            roomidList.add(s.getRoomId());
        }
        return roomidList;
    }

    @GetMapping("/room/insert")
    public Result insert(@RequestParam("roomId") Integer roomId,@RequestParam("startTime")Date startDate,@RequestParam("endTime")Date endDate, HttpSession session){
        Subscribe subscribe = new Subscribe(roomId, startDate, endDate);
        String openId = (String) session.getAttribute("openId");
        subscribe.setUserOpenid(openId);
        int row = subscribeMapper.insertSelective(subscribe);
        if (row == 1) {
            return Result.success(subscribe);
        } else {
            return Result.fail("新增失败");
        }
    }
}
