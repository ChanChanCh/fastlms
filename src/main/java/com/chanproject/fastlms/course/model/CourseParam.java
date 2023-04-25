package com.chanproject.fastlms.course.model;

import com.chanproject.fastlms.admin.model.CommonParam;
import lombok.Data;

@Data
public class CourseParam extends CommonParam {

    long id; // course.id
    long categoryId;
}
