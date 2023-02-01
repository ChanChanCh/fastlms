package com.chanproject.fastlms.member.controller;

import com.chanproject.fastlms.member.model.MemberInput;
import com.chanproject.fastlms.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Controller
public class MemberController {


    private final MemberService memberService;

    // get과post 둘다 받을수있또록 RequesetMapping를 사용
    @RequestMapping("/member/login")
    public String login(){

        return "member/login";
    }

    @GetMapping("/member/register")
    public String register(){

        return "member/register";
    }

//  request  web -> server
//  response server -> web

    @PostMapping("/member/register")
    public String registerSubmit(Model model, HttpServletRequest request
                                 , MemberInput parameter){

        boolean result = memberService.register(parameter);
        model.addAttribute("result", result);

        return "member/register_complete";
    }

    @GetMapping("/member/email-auth")
    public String emailAuth(Model model, HttpServletRequest request) {

        String uuid = request.getParameter("id");
        System.out.println(uuid);

        boolean result = memberService.emailAuth(uuid);
        model.addAttribute("result",result);

        return "member/email_auth";
    }

    @GetMapping("/member/info")
    public String memberinfo(Model model, HttpServletRequest request) {



        return "member/info";
    }


}
