package com.chanproject.fastlms.member.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data       //getter setter
@Entity    // 테이블
public class Member {
    @Id
    private String userId;

    private String userName;
    private String password;
    private String phone;
    private LocalDateTime regDt;

    private boolean emailAuthYn;
    private String emailAuthKey;
    private  LocalDateTime emailAuthDt;

    private String resetPasswordKey;
    private  LocalDateTime resetPasswordLimitDt;

    //관리자 판단
    private boolean adminYn;

}
