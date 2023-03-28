package com.chanproject.fastlms.admin.repository;


import com.chanproject.fastlms.admin.entity.Category;
import com.chanproject.fastlms.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {


}
