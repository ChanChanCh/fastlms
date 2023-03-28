package com.chanproject.fastlms.member.service;

import com.chanproject.fastlms.admin.dto.MemberDto;
import com.chanproject.fastlms.admin.model.MemberParam;
import com.chanproject.fastlms.member.model.MemberInput;
import com.chanproject.fastlms.member.model.ResetPasswordInput;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface MemberService extends UserDetailsService {

    boolean register(MemberInput parameter);


    /**
     * uuid에 해당하는 계정을 활성화 함.
     */
    boolean emailAuth(String uuid);

    /**
     * 입력한 이메일로 비밀번호 초기화 정보 전송
     */

    boolean sendResetPassword(ResetPasswordInput parameter);

    /**
     *  입력받은 uuid에 대해서  password로 초기화 함
     */
    boolean resetPassword(String uuid, String password);

    /**
     *  입력받은 uuid값이 유효한지 확인
     */

    boolean checkResetPassword(String uuid);

    /**
     * 회원 목록 리턴 (관리자에서만 사용 가능)
     */

    List<MemberDto> list(MemberParam parameter);

    /**
     * 회원 상세 정보
     */

    MemberDto detail(String userId);


}
