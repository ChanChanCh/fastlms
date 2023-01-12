package com.chanproject.fastlms.member;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MemberController {

    @GetMapping("/member/register")
    public String register(){

        System.out.println("request get!!!");

        return "member/register";
    }

//  request  web -> server
//  response server -> web

    @PostMapping("/member/register")
    public String registerSubmit(HttpServletRequest request, HttpServletResponse response
                                 ,MemberInput parameter){

//        String userId = request.getParameter("userId");
//        String userName = request.getParameter("userName");
//        String password = request.getParameter("password");
//        String phone = request.getParameter("phone");
//
//        System.out.println("userId" + userId);
//        System.out.println("userName" + userName);
//        System.out.println("password" + password);
//        System.out.println("phone" + phone);

        System.out.println(parameter.toString());

        return "member/register";

    }


    @RequestMapping("/member/login")
    public String login(){

        return "member/login";
    }

    @RequestMapping("/member/logout")
    public String logout(){

        return "member/logout";
    }





}
