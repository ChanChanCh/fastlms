package com.chanproject.fastlms.course.controller;

import com.chanproject.fastlms.admin.service.CategoryService;
import com.chanproject.fastlms.course.dto.CourseDto;
import com.chanproject.fastlms.course.dto.TakeCourseDto;
import com.chanproject.fastlms.course.model.TakeCourseParam;
import com.chanproject.fastlms.course.service.TakeCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@RequiredArgsConstructor
@Controller
public class AdminTakeCourseController extends BaseController{

    private final TakeCourseService takeCourseService;
    private final CategoryService categoryService;


    @GetMapping("/admin/takecourse/list.do")
    public String list(Model model, TakeCourseParam parameter){

        parameter.init();

        List<TakeCourseDto> courseList = takeCourseService.list(parameter);

        long totalCount = 0;
        if (!CollectionUtils.isEmpty(courseList)) {
            totalCount = courseList.get(0).getTotalCount();
        }
        String queryString = parameter.getQueryString();
        String pagerHtml = getPaperHtml(totalCount, parameter.getPageSize(), parameter.getPageIndex(), queryString);

        model.addAttribute("list", courseList);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("pager", pagerHtml);

        return "admin/takecourse/list";
    }


}
