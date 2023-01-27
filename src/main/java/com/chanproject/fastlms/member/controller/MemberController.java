package com.chanproject.fastlms.member.controller;

import com.chanproject.fastlms.member.entity.Member;
import com.chanproject.fastlms.member.model.MemberInput;
import com.chanproject.fastlms.member.repository.MemberRepository;
import com.chanproject.fastlms.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Controller
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/member/register")
    public String register(){

        System.out.println("request get!!!");

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


    @RequestMapping("/member/login")
    public String login(){

        return "member/login";
    }

    @RequestMapping("/member/logout")
    public String logout(){

        return "member/logout";
    }


}
