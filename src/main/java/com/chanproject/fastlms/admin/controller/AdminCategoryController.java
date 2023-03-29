package com.chanproject.fastlms.admin.controller;

import com.chanproject.fastlms.admin.dto.CategoryDto;
import com.chanproject.fastlms.admin.dto.MemberDto;
import com.chanproject.fastlms.admin.model.CategoryInput;
import com.chanproject.fastlms.admin.model.MemberParam;
import com.chanproject.fastlms.admin.service.CategoryService;
import com.chanproject.fastlms.member.service.MemberService;
import com.chanproject.fastlms.util.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@RequiredArgsConstructor
@Controller
public class AdminCategoryController {


    private final CategoryService categoryService;

    @GetMapping("/admin/category/list.do")
    public String list(Model model, MemberParam parameter){

        List<CategoryDto> list = categoryService.list();
        model.addAttribute("list", list);

        return "admin/category/list";
    }

    @PostMapping("/admin/category/add.do")
    public String add(Model model, CategoryInput parameter){

       boolean result = categoryService.add(parameter.getCategoryName());

        return "redirect:/admin/category/list.do";

    }

    @PostMapping("/admin/category/delete.do")
    public String del(Model model, CategoryInput parameter){

        boolean result = categoryService.del(parameter.getId());

        return "redirect:/admin/category/list.do";

    }

}
