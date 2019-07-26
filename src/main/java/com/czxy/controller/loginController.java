package com.czxy.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 刘昌燊
 * @version v 1.0
 * @date 2019/7/18
 */
@RestController
@RequestMapping("/login")
public class loginController {

    @PostMapping()
    public ResponseEntity<Void>login(String username, String password, String isCheck, HttpServletResponse response, HttpServletRequest request){
        System.out.println(username);
        System.out.println(password);
        System.out.println(isCheck);
        boolean flag=false;
        //用户名密码 正确
         if ("admin".equals(username)&&"admin".equals(password)){
             flag=true;
         }
         //失败 500状态码
         if (!flag){
             return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR );
         }
         //创建3个cookie对象
        Cookie c1 = new Cookie("username",username);
        Cookie c2 = new Cookie("password",password);
        Cookie c3 = new Cookie("isCheck","on");
        //设置全局
        c1.setPath("/");
        c2.setPath("/");
        c3.setPath("/");

        //如果勾选了isCheck
        if ("on".equals(isCheck)){

        }else {
            //否则销毁三个cookie
            c1.setMaxAge(0);
            c2.setMaxAge(0);
            c3.setMaxAge(0);
        }
        //把cookie响应给浏览器
        response.addCookie(c1);
        response.addCookie(c2);
        response.addCookie(c3);

        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
}
