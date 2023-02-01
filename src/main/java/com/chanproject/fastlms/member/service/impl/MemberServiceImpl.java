package com.chanproject.fastlms.member.service.impl;

import com.chanproject.fastlms.components.MailComponents;
import com.chanproject.fastlms.member.entity.Member;
import com.chanproject.fastlms.member.exception.MemberNotEmailAuthException;
import com.chanproject.fastlms.member.model.MemberInput;
import com.chanproject.fastlms.member.repository.MemberRepository;
import com.chanproject.fastlms.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private  final MailComponents mailComponents;


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

        String encPassword = BCrypt.hashpw(parameter.getPassword(), BCrypt.gensalt());
        String uuid = UUID.randomUUID().toString();

        Member member = Member.builder()
                .userId(parameter.getUserId())
                .userName(parameter.getUserName())
                .phone(parameter.getPhone())
                .password(encPassword)
                .regDt(LocalDateTime.now())
                .emailAuthYn(false)
                .emailAuthKey(uuid)
                .build();
        memberRepository.save(member);

        String email = parameter.getUserId();
        String subject = "바인홀 사이트 가입을 축하드립니다. ";
        String text = "<p>바인홀 사이트 가입을 축하드립니다.<p><p> 아래 링크를 클릭하신후 가입을 완료하세요<p>"
                + "<div><a target='_blank' href='http://localhost:8080/member/email-auth?id="+ uuid + "'> 가입완료 </a></div>";
        mailComponents.sendMail(email, subject, text);

        return true;
    }
    @Override
    public boolean emailAuth(String uuid) {

        Optional<Member> optionalMember = memberRepository.findByEmailAuthKey(uuid);
        if(!optionalMember.isPresent()){
            return false;
        }

        Member member = optionalMember.get();
        member.setEmailAuthYn(true);
        member.setEmailAuthDt((LocalDateTime.now()));
        memberRepository.save(member);

        return true;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Member> optionalMember = memberRepository.findById(username);
        if(!optionalMember.isPresent()) {
            throw new UsernameNotFoundException("회원 정보가 존재하지 않습니다.");
        }

        Member member = optionalMember.get();

        if(!member.isEmailAuthYn()){
            throw new MemberNotEmailAuthException(" 이메일 인증을 해야 로그인이 가능합니다. ");
        }

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));



        return new User(member.getUserName(), member.getPassword(), grantedAuthorities);
    }
}
