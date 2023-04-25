package com.chanproject.fastlms.course.service;


import com.chanproject.fastlms.course.dto.TakeCourseDto;
import com.chanproject.fastlms.course.model.ServiceResult;
import com.chanproject.fastlms.course.model.TakeCourseParam;

import java.util.List;

public interface TakeCourseService {


    /**
     * 수강 목록
     */
    List<TakeCourseDto> list(TakeCourseParam parameter);

    /**
     *  수강내용 상태 변경
     */

    ServiceResult updateStatus(long id, String status);


}
