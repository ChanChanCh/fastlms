package com.chanproject.fastlms.course.service.impl;


import com.chanproject.fastlms.course.dto.TakeCourseDto;
import com.chanproject.fastlms.course.mapper.TakeCourseMapper;
import com.chanproject.fastlms.course.model.TakeCourseParam;
import com.chanproject.fastlms.course.service.TakeCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TakeCourseServiceImpl implements TakeCourseService {


    private final TakeCourseMapper takeCourseMapper;


    @Override
    public List<TakeCourseDto> list(TakeCourseParam parameter) {

        long totalCount = takeCourseMapper.selectListCount(parameter);

        List<TakeCourseDto> list = takeCourseMapper.selectList(parameter);

        if(!CollectionUtils.isEmpty(list)){
            int i = 0;
            for(TakeCourseDto x : list){
                x.setTotalCount(totalCount);
                x.setSeq(totalCount - parameter.getPageStart() - i );
                i++;
            }
        }
        return list;
    }
}
