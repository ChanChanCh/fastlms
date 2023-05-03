package com.chanproject.fastlms.course.model;


import lombok.Data;

@Data
public class CourseInput {

    long id;
    long categoryId;
    String subject;
    String keyword;
    String summary;
    String contents;
    long price;
    long salePrice;
    String saleEndDtText;

    // 삭제
    String idList;

    // ADD
    String filename;



}
