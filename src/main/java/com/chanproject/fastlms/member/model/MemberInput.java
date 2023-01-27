package com.chanproject.fastlms.member.model;


import lombok.Data;
import lombok.ToString;

@ToString
//자동으로 getter setter 지원
@Data
public class MemberInput {

    private String userId;
    private String userName;
    private String password;
    private String phone;

}
