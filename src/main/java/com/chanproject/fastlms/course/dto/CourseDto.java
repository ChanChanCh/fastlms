package com.chanproject.fastlms.course.dto;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CourseDto {

    Long id;
    String imagePath;
    String keyword;
    String subject;
    String summary;
    String contents;
    long price;
    long salePrice;
    LocalDateTime saleEndDt;
    LocalDateTime regDt; // 등록날짜
    LocalDateTime udtDt; // 수정날짜

    //추가칼럼
    long totalCount;
    long seq;

}


