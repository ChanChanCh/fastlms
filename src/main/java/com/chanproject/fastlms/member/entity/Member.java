package com.chanproject.fastlms.member.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity    // 테이블
public class Member {
    @Id

    private String userId;
    private String userName;
    private String password;
    private String phone;
    private LocalDateTime regDt;



}
