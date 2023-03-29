package com.chanproject.fastlms.course.service.impl;


import com.chanproject.fastlms.course.dto.CourseDto;
import com.chanproject.fastlms.course.entity.Course;
import com.chanproject.fastlms.course.mapper.CourseMapper;
import com.chanproject.fastlms.course.model.CourseInput;
import com.chanproject.fastlms.course.model.CourseParam;
import com.chanproject.fastlms.course.repository.CourseRepository;
import com.chanproject.fastlms.course.service.CourseService;
import com.chanproject.fastlms.admin.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;


    @Override
    public boolean add(CourseInput parameter) {

        Course course = Course.builder()
                .subject(parameter.getSubject())
                .regDt(LocalDateTime.now())
                .build();
        courseRepository.save(course);

        return true;
    }

    @Override
    public List<CourseDto> list(CourseParam parameter) {

        long totalCount = courseMapper.selectListCount(parameter);

        List<CourseDto> list = courseMapper.selectList(parameter);

        if(!CollectionUtils.isEmpty(list)){
            int i = 0;
            for(CourseDto x : list){
                x.setTotalCount(totalCount);
                x.setSeq(totalCount - parameter.getPageStart() - i );
                i++;
            }
        }
        return list;
    }
}
