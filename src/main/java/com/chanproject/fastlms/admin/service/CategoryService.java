package com.chanproject.fastlms.admin.service;


import com.chanproject.fastlms.admin.dto.CategoryDto;
import com.chanproject.fastlms.admin.entity.Category;
import com.chanproject.fastlms.admin.model.CategoryInput;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryService {

    List<CategoryDto> list();

    /**
     *  카테고리 신규추가
     */
    boolean add(String categoryName);
    /**
     *  카테고리 수정
     */
    boolean update(CategoryInput parameter);
    /**
     *  카테고리 삭제
     */
    boolean del(long id);

    /**
     * 프론트 카테고리 정보
     */

    List<CategoryDto> frontList(CategoryDto parameter);


}
