package com.chanproject.fastlms.member.service;

import com.chanproject.fastlms.member.model.MemberInput;

public interface MemberService {

    boolean register(MemberInput parameter);


    /**
     * uuid에 해당하는 계정을 활성화 함.
     */
    boolean emailAuth(String uuid);

}
