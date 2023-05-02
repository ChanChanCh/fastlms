package com.chanproject.fastlms.course.controller;

import com.chanproject.fastlms.course.dto.CourseDto;
import com.chanproject.fastlms.course.dto.TakeCourseDto;
import com.chanproject.fastlms.course.model.ServiceResult;
import com.chanproject.fastlms.course.model.TakeCourseParam;
import com.chanproject.fastlms.course.service.CourseService;
import com.chanproject.fastlms.course.service.TakeCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@RequiredArgsConstructor
@Controller
public class AdminTakeCourseController extends BaseController{

    private final CourseService courseService;
    private final TakeCourseService takeCourseService;


    @GetMapping("/admin/takecourse/list.do")
    public String list(Model model, TakeCourseParam parameter
            , BindingResult bindingResult
    ){
        // 바인딩이 되지않을때 사용하는 이벤트 ( searchCourseId가 정수형인데 null이들어와서 에러가 남 )
//        if(bindingResult.hasErrors()){
//            System.out.println("#####################");
//            System.out.println(bindingResult.getAllErrors());
//        }

        parameter.init();

        List<TakeCourseDto> list = takeCourseService.list(parameter);

        long totalCount = 0;
        if (!CollectionUtils.isEmpty(list)) {
            totalCount = list.get(0).getTotalCount();
        }
        String queryString = parameter.getQueryString();
        String pagerHtml = getPaperHtml(totalCount, parameter.getPageSize(), parameter.getPageIndex(), queryString);

        model.addAttribute("list", list);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("pager", pagerHtml);


        List<CourseDto> courseList = courseService.listAll();
        model.addAttribute("courseList",courseList);


        return "admin/takecourse/list";
    }

    @PostMapping("/admin/takecourse/status.do")
    public String status(Model model, TakeCourseParam parameter){

            ServiceResult result = takeCourseService.updateStatus(parameter.getId(), parameter.getStatus());
            if(!result.isResult()){
                model.addAttribute("message", result.getMessage());
                return "common/error";
            }

        return "redirect:/admin/takecourse/list.do";
    }

}
