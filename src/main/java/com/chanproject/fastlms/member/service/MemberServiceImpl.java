package com.chanproject.fastlms.member.service;

import com.chanproject.fastlms.member.entity.Member;
import com.chanproject.fastlms.member.model.MemberInput;
import com.chanproject.fastlms.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    /**
     * 회원 가입
     */

    @Override
    public boolean register(MemberInput parameter) {

        // null값처리를 위해 Optional 사용
        Optional<Member> optionalMember = memberRepository.findById(parameter.getUserId());
        if(optionalMember.isPresent()){
            // 현재 userId에 해당하는 데이터 존재   (아이디값이 존재하다면 저장을 하지않게 하기위함)
            return false;
        }

        memberRepository.findById(parameter.getUserId());

        Member member = new Member();
        member.setUserId(parameter.getUserId());
        member.setUserName(parameter.getUserName());
        member.setPhone(parameter.getPhone());
        member.setPassword(parameter.getPassword());
        member.setRegDt(LocalDateTime.now());

        memberRepository.save(member);

        return false;
    }
}
