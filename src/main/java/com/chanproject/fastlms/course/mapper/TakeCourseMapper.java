package com.chanproject.fastlms.course.mapper;

import com.chanproject.fastlms.course.dto.TakeCourseDto;
import com.chanproject.fastlms.course.model.TakeCourseParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TakeCourseMapper {

    long selectListCount(TakeCourseParam parameter);
    List<TakeCourseDto> selectList(TakeCourseParam parameter);


}
