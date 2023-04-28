package com.chanproject.fastlms.course.service.impl;


import com.chanproject.fastlms.course.dto.TakeCourseDto;
import com.chanproject.fastlms.course.entity.TakeCourse;
import com.chanproject.fastlms.course.entity.TakeCourseCode;
import com.chanproject.fastlms.course.mapper.TakeCourseMapper;
import com.chanproject.fastlms.course.model.ServiceResult;
import com.chanproject.fastlms.course.model.TakeCourseParam;
import com.chanproject.fastlms.course.repository.TakeCourseRepository;
import com.chanproject.fastlms.course.service.TakeCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TakeCourseServiceImpl implements TakeCourseService {


    private final TakeCourseMapper takeCourseMapper;
    private final TakeCourseRepository takeCourseRepository;

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

    @Override
    public TakeCourseDto  detail(long id) {


        Optional<TakeCourse> optionalTakeCourse = takeCourseRepository.findById(id);
        if(optionalTakeCourse.isPresent()){
            return TakeCourseDto.of(optionalTakeCourse.get());
        }
        return null;
    }

    @Override
    public ServiceResult updateStatus(long id, String status) {

        Optional<TakeCourse> optionalTakeCourse = takeCourseRepository.findById(id);
        if(!optionalTakeCourse.isPresent()){
            return new ServiceResult(false, "수강 정보가 존재하지 않습니다.");
        }

        TakeCourse takeCourse = optionalTakeCourse.get();

        takeCourse.setStatus(status);
        takeCourseRepository.save(takeCourse);

        return new ServiceResult(true);

    }

    @Override
    public List<TakeCourseDto> myCourse(String userId) {

        TakeCourseParam parameter = new TakeCourseParam();
        parameter.setUserId(userId);
        List<TakeCourseDto> list = takeCourseMapper.selectListMyCourse(parameter);

        return list;
    }

    @Override
    public ServiceResult cancel(long id) {
        Optional<TakeCourse> optionalTakeCourse = takeCourseRepository.findById(id);
        System.out.println(id);
        if(!optionalTakeCourse.isPresent()){
            return new ServiceResult(false, "수강 정보가 존재하지 않습니다.");
        }

        TakeCourse takeCourse = optionalTakeCourse.get();

        takeCourse.setStatus(TakeCourseCode.STATUS_CANCEL);
        takeCourseRepository.save(takeCourse);

        return new ServiceResult();
    }
}
