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
import java.time.format.DateTimeFormatter;

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
        LocalDateTime udtDt;


        boolean emailAuthYn;
        String emailAuthKey;
        LocalDateTime emailAuthDt;
        String resetPasswordKey;
        LocalDateTime resetPasswordLimitDt;
        boolean adminYn;
        String userStatus;


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
                        .udtDt(member.getUdtDt())
                        .emailAuthYn(member.isEmailAuthYn())
                        .emailAuthDt(member.getEmailAuthDt())
                        .emailAuthKey(member.getEmailAuthKey())
                        .resetPasswordKey(member.getResetPasswordKey())
                        .resetPasswordLimitDt(member.getResetPasswordLimitDt())
                        .adminYn(member.isAdminYn())
                        .userStatus(member.getUserStatus())
                        .build();

        }

        public String getRegDtText(){

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
                return regDt != null ? regDt.format(formatter) : "";
        }

        public String getUdtDtText(){

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
                return regDt != null ? regDt.format(formatter) : "";
        }


}
