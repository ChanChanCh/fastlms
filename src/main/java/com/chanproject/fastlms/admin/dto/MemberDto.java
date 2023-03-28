package com.chanproject.fastlms.admin.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MemberDto {

        String userId;
        String userName;
        String password;
        String phone;
        LocalDateTime regDt;

        boolean emailAuthYn;
        String emailAuthKey;
        LocalDateTime emailAuthDt;

        String resetPasswordKey;
        LocalDateTime resetPasswordLimitDt;

        boolean adminYn;

        // 추가컬럼
        long totalCount;
        long seq;



}
