package com.chanproject.fastlms.admin;

import com.chanproject.fastlms.admin.dto.MemberDto;
import com.chanproject.fastlms.member.entity.Member;
import com.chanproject.fastlms.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@RequiredArgsConstructor
@Controller
public class AdminMemberController {

    private  final MemberService memberService;

    @GetMapping("/admin/member/list.do")
    public String list(Model model){

        List<MemberDto> members = memberService.list();

        model.addAttribute("list", members);

        return "admin/member/list";

    }



}
