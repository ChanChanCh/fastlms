package com.chanproject.fastlms.member.controller;

import com.chanproject.fastlms.member.entity.Member;
import com.chanproject.fastlms.member.model.MemberInput;
import com.chanproject.fastlms.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@Controller
public class MemberController {



    private final MemberRepository memberRepository;

   public MemberController(MemberRepository memberRepository){
       this.memberRepository = memberRepository;
   }




    @GetMapping("/member/register")
    public String register(){

        System.out.println("request get!!!");

        return "member/register";
    }

//  request  web -> server
//  response server -> web

    @PostMapping("/member/register")
    public String registerSubmit(HttpServletRequest request, HttpServletResponse response
                                 , MemberInput parameter){

        System.out.println(parameter.toString());

        Member member = new Member();
        member.setUserId(parameter.getUserId());
        member.setUserName(parameter.getUserName());
        member.setPhone(parameter.getPhone());
        member.setPassword(parameter.getPassword());
        member.setRegDt(LocalDateTime.now());

        memberRepository.save(member);

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
