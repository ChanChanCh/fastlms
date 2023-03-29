package com.chanproject.fastlms.course.service;


import com.chanproject.fastlms.course.dto.CourseDto;
import com.chanproject.fastlms.course.model.CourseInput;
import com.chanproject.fastlms.course.model.CourseParam;

import java.util.List;

public interface CourseService {


    /**
    * 강좌 등록
    */

    boolean add(CourseInput parameter);

    /**
     * 강좌 목록
     */
    List<CourseDto> list(CourseParam parameter);


}
