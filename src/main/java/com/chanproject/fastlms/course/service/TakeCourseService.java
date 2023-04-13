package com.chanproject.fastlms.course.service;


import com.chanproject.fastlms.course.dto.TakeCourseDto;
import com.chanproject.fastlms.course.model.TakeCourseParam;

import java.util.List;

public interface TakeCourseService {


    /**
     * 수강 목록
     */
    List<TakeCourseDto> list(TakeCourseParam parameter);


}
