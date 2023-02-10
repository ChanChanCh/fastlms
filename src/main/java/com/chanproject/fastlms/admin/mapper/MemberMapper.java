package com.chanproject.fastlms.admin.mapper;


import com.chanproject.fastlms.admin.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {


    List<MemberDto> selectList(MemberDto parameter);


}
