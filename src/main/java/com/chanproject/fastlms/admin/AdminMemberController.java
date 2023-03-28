package com.chanproject.fastlms.admin;

import com.chanproject.fastlms.admin.dto.MemberDto;
import com.chanproject.fastlms.admin.model.MemberParam;
import com.chanproject.fastlms.member.entity.Member;
import com.chanproject.fastlms.member.repository.MemberRepository;
import com.chanproject.fastlms.member.service.MemberService;
import com.chanproject.fastlms.util.PageUtil;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Controller
public class AdminMemberController {

    private  final MemberService memberService;
    private final MemberRepository memberRepository;


    @GetMapping("/admin/member/list.do")
    public String list(Model model, MemberParam parameter){

        parameter.init();

        List<MemberDto> members = memberService.list(parameter);

        long totalCount = 0;
        if (members != null && members.size() > 0) {
            totalCount = members.get(0).getTotalCount();
        }
        String queryString = parameter.getQueryString();

        PageUtil pageUtil = new PageUtil(totalCount, parameter.getPageSize(), parameter.getPageIndex(), queryString);

        model.addAttribute("list", members);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("pager", pageUtil.pager());

        return "admin/member/list";
        
    }

    @GetMapping("/admin/member/detail.do")
    public String detail(Model model, MemberParam parameter){

        parameter.init();

        MemberDto member = memberService.detail(parameter.getUserId());
        model.addAttribute("member", member);

        return "admin/member/detail";

    }

}
