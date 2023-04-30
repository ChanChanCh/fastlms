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
public class Member implements MemberCode {
    @Id
    private String userId;

    private String userName;
    private String password;
    private String phone;
    private LocalDateTime regDt;
    private LocalDateTime udtDt; // 회원정보 수정일

    private boolean emailAuthYn;
    private String emailAuthKey;
    private  LocalDateTime emailAuthDt;

    private String resetPasswordKey;
    private  LocalDateTime resetPasswordLimitDt;

    //관리자 판단
    private boolean adminYn;

    private String userStatus; // 이용가능과 정지상태 구분

    private String  zipcode;
    private String  addr;
    private String addrDetail;

}
