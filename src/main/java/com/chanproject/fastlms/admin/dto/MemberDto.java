package com.chanproject.fastlms.admin.dto;

import com.chanproject.fastlms.member.entity.Member;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
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

        public static MemberDto of(Member member) {

                return MemberDto.builder()
                        .userId(member.getUserId())
                        .userName(member.getUserName())
                        .phone(member.getPhone())
//                        .password(member.getPassword())
                        .regDt(member.getRegDt())
                        .emailAuthYn(member.isEmailAuthYn())
                        .emailAuthDt(member.getEmailAuthDt())
                        .emailAuthKey(member.getEmailAuthKey())
                        .resetPasswordKey(member.getResetPasswordKey())
                        .resetPasswordLimitDt(member.getResetPasswordLimitDt())
                        .adminYn(member.isAdminYn())
                        .build();

        }

}
