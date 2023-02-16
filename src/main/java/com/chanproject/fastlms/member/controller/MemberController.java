package com.chanproject.fastlms.member.controller;

import com.chanproject.fastlms.member.model.MemberInput;
import com.chanproject.fastlms.member.model.ResetPasswordInput;
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

    @GetMapping("/member/find/password")
    public String findPassword(){

        return "member/find_password";
    }

    @PostMapping("/member/find/password")
    public String findPasswordSubmit(Model model, ResetPasswordInput parameter){

        boolean result = false;
        try {
            result = memberService.sendResetPassword(parameter);

        } catch (Exception e){

        }
        model.addAttribute("result", result);

        return "member/find_password_result";
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
    public String memberInfo(Model model, HttpServletRequest request) {


        return "member/info";
    }

    @GetMapping("/member/reset/password")
    public String resetPassword(Model model, HttpServletRequest request){


        String uuid = request.getParameter("id");

        boolean result = memberService.checkResetPassword(uuid);

        model.addAttribute("result", result);

        return "member/reset_password";
    }

    @PostMapping("/member/reset/password")
    public String resetPasswordSubmit(Model model, ResetPasswordInput parameter){

        boolean result = false;
        try {
            result = memberService.resetPassword(parameter.getId(), parameter.getPassword());
        } catch (Exception e){
        }

        model.addAttribute("result", result);

        return "member/reset_password_result";
    }


}
