package com.went.springboot.controller;


import com.went.springboot.mapper.SubscribeMapper;
import com.went.springboot.mapper.UserInfoMapper;
import com.went.springboot.pojo.SubscribeExample;
import com.went.springboot.pojo.UserInfo;
import com.went.springboot.pojo.UserInfoExample;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.oauth2.WxOAuth2AccessToken;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.service.WxOAuth2Service;
import me.chanjar.weixin.mp.api.WxMpService;

import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author mys
 * @date 2022/3/23 15:44
 */
@Controller
//@RequestMapping("/wechat")
@Slf4j
public class WechatController {

    @Autowired
    private WxMpService wxMpService;
    @Autowired
    private UserInfoMapper userInfoMapper;

    @GetMapping("/authorize")
    public String authorize()
     {
        //先在application.xml中配置appid和secret，再调用方法
        // String echostr =() model.getAttribute("echostr");
         //1.构造网页授权url
        String url = "http://66u6xu.natappfree.cc/userInfo";
        //调用此方法跳转到redirectUrl便于观看结果
        String redirectUrl =wxMpService.getOAuth2Service().buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_USERINFO, null);
        return "redirect:" + redirectUrl;
    }

    @GetMapping("/userInfo")
    public String userInfo(@RequestParam("code") String code, HttpSession session) {
            //2.获取access token
        WxOAuth2AccessToken wxOAuth2AccessToken = null;
        String openId = null;
        try {
            assert wxOAuth2AccessToken != null;
            WxOAuth2Service oAuth2Service = wxMpService.getOAuth2Service();
            WxOAuth2AccessToken accessToken = oAuth2Service.getAccessToken(code);
//             wxOAuth2AccessToken = wxMpService.getOAuth2Service().getAccessToken(code);
           openId = accessToken.getOpenId();
        } catch (WxErrorException e) {
            log.error("【微信网页授权】{}", e);
            e.printStackTrace();
        }
        //3.获取openid
        UserInfoExample example = new UserInfoExample();

        UserInfoExample.Criteria criteria = example.createCriteria();
        criteria.andUserOpenidEqualTo(openId);
        List<UserInfo> userInfos = userInfoMapper.selectByExample(example);
        UserInfo user = new UserInfo();
        user.setUserOpenid(openId);
        if(userInfos.size()==0){
            userInfoMapper.insertSelective(user);
        }
       session.setAttribute("openId",openId);
        return "redirect:hello" + "?openid=" + openId;
    }
}

