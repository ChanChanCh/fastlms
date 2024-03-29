package com.chanproject.fastlms.member.service.impl;

import com.chanproject.fastlms.admin.dto.MemberDto;
import com.chanproject.fastlms.admin.mapper.MemberMapper;
import com.chanproject.fastlms.admin.model.MemberParam;
import com.chanproject.fastlms.components.MailComponents;
import com.chanproject.fastlms.course.model.ServiceResult;
import com.chanproject.fastlms.member.entity.Member;
import com.chanproject.fastlms.member.exception.MemberNotEmailAuthException;
import com.chanproject.fastlms.member.exception.MemberStopUserException;
import com.chanproject.fastlms.member.model.MemberInput;
import com.chanproject.fastlms.member.model.ResetPasswordInput;
import com.chanproject.fastlms.member.repository.MemberRepository;
import com.chanproject.fastlms.member.service.MemberService;
import com.chanproject.fastlms.util.PasswordUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
    private  final MemberMapper memberMapper;


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
                .userStatus(Member.MEMBER_STATUS_REQ)
                .build();
        memberRepository.save(member);

        String email = parameter.getUserId();
        String subject = "가입확인 인증메일입니다. ";
        String text = "<p>가입확인 인증메일입니다.<p><p> 아래 링크를 클릭하신후 가입을 완료하세요<p>"
                + "<div><a target='_blank' href='http://localhost:8080/member/email-auth?id="+ uuid + "'> 가입완료 </a></div>";
        mailComponents.sendMail(email, subject, text);

        return true;
    }

    /*
    이메일 인증
     */
    @Override
    public boolean emailAuth(String uuid) {

        Optional<Member> optionalMember = memberRepository.findByEmailAuthKey(uuid);
        if(!optionalMember.isPresent()){
            return false;
        }
        Member member = optionalMember.get();

        // 이미 활성화 시킨 이메일 인증작업을 다시누르면 활성화 되지않도록 만든장치
        if(member.isEmailAuthYn()) {
            return false;
        }

        member.setUserStatus(Member.MEMBER_STATUS_ING);
        member.setEmailAuthYn(true);
        member.setEmailAuthDt(LocalDateTime.now());
        memberRepository.save(member);

        return true;

    }

    @Override
    public boolean sendResetPassword(ResetPasswordInput parameter) {

        Optional<Member> optionalMember = memberRepository.findByUserIdAndUserName(parameter.getUserId(), parameter.getUserName());
        if(!optionalMember.isPresent()) {
            throw new UsernameNotFoundException("회원 정보가 존재하지 않습니다.");
        }

        Member member = optionalMember.get();

        String uuid = UUID.randomUUID().toString();

        member.setResetPasswordKey(uuid);
        member.setResetPasswordLimitDt(LocalDateTime.now().plusDays(1));
        memberRepository.save(member);


        String email = parameter.getUserId();
        String subject = "[관리자] 비밀번호 초기화 메일입니다. ";
        String text = "<p> 비밀번호 초기화 메일입니다 <p>" +
                "<p> 아래 링크를 클릭하신후 비밀번호를 초기화 해주세요.<p>" +
                "<div><a target='_blank' href='http://localhost:8080/member/reset/password?id="+ uuid + "'> 비밀번호 초기화 링크 </a></div>";
        mailComponents.sendMail(email, subject, text);


        return true;
    }

    @Override
    public boolean resetPassword(String uuid, String password) {

        Optional<Member> optionalMember = memberRepository.findByResetPasswordKey(uuid);
        if(!optionalMember.isPresent()) {
            throw new UsernameNotFoundException("회원 정보가 존재하지 않습니다.");
        }

        Member member = optionalMember.get();

        //초기화 날짜가 유효한지 체크
        if (member.getResetPasswordLimitDt() == null){
            throw new RuntimeException(" 유효한 날짜가 아닙니다. ");
        }

        if(member.getResetPasswordLimitDt().isBefore(LocalDateTime.now())){
            throw new RuntimeException(" 유효한 날짜가 아닙니다. ");
        }

        String encPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        member.setPassword(encPassword);
        member.setResetPasswordKey("");
        member.setResetPasswordLimitDt(null);
        memberRepository.save(member);

        return true;
    }

    @Override
    public boolean checkResetPassword(String uuid) {

        Optional<Member> optionalMember = memberRepository.findByResetPasswordKey(uuid);
        if(!optionalMember.isPresent()) {
            return false;
        }

        Member member = optionalMember.get();

        //초기화 날짜가 유효한지 체크
        if (member.getResetPasswordLimitDt() == null){
            throw new RuntimeException(" 유효한 날짜가 아닙니다. ");
        }

        if(member.getResetPasswordLimitDt().isBefore(LocalDateTime.now())){
            throw new RuntimeException(" 유효한 날짜가 아닙니다. ");
        }

        return true;
    }

    @Override
    public List<MemberDto> list(MemberParam parameter) {

        long totalCount = memberMapper.selectListCount(parameter);

        List<MemberDto> list = memberMapper.selectList(parameter);

        if(!CollectionUtils.isEmpty(list)){
            int i = 0;
            for(MemberDto x : list){
                x.setTotalCount(totalCount);
                x.setSeq(totalCount - parameter.getPageStart() - i );
                i++;
            }
        }
        return list;

//        return memberRepository.findAll();
    }

    @Override
    public MemberDto detail(String userId) {

        Optional<Member> optionalMember = memberRepository.findById(userId);
        if(!optionalMember.isPresent()){
            return null;
        }

        Member member = optionalMember.get();

        return MemberDto.of(member);
    }

    @Override
    public boolean updateStatus(String userId, String userStatus) {

        Optional<Member> optionalMember = memberRepository.findById(userId);
        if(!optionalMember.isPresent()) {
            throw new UsernameNotFoundException("회원 정보가 존재하지 않습니다.");
        }

        Member member = optionalMember.get();

        member.setUserStatus(userStatus);
        memberRepository.save(member);

        return true;
    }

    @Override
    public boolean updatePassword(String userId, String password) {

        Optional<Member> optionalMember = memberRepository.findById(userId);
        if(!optionalMember.isPresent()) {
            throw new UsernameNotFoundException("회원 정보가 존재하지 않습니다.");
        }

        Member member = optionalMember.get();

        String encPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        member.setPassword(encPassword);
        memberRepository.save(member);

        return true;
    }

    @Override
    public ServiceResult updateMember(MemberInput parameter) {

        String userId = parameter.getUserId();

        Optional<Member> optionalMember = memberRepository.findById(userId);
        if(!optionalMember.isPresent()) {
            return new ServiceResult(false,"회원 정보가 존재하지 않습니다.");
        }

        Member member = optionalMember.get();

        member.setPhone(parameter.getPhone());
        member.setZipcode(parameter.getZipcode());
        member.setAddr(parameter.getAddr());
        member.setAddrDetail(parameter.getAddrDetail());
        member.setUdtDt(LocalDateTime.now());
        memberRepository.save(member);

        return new ServiceResult();
    }


    @Override
    public ServiceResult updateMemberPassword(MemberInput parameter) {

        String userId = parameter.getUserId();

        Optional<Member> optionalMember = memberRepository.findById(userId);
        if(!optionalMember.isPresent()) {
            return new ServiceResult(false,"회원 정보가 존재하지 않습니다.");
        }

        Member member = optionalMember.get();

        if(!PasswordUtils.equals(parameter.getPassword(), member.getPassword())){
            return new ServiceResult(false,"비밀번호가 일치하지 않습니다.");
        }

        String encPassword = PasswordUtils.encPassword(parameter.getNewPassword());
        member.setPassword(encPassword);
        memberRepository.save(member);

        return new ServiceResult(true);
    }

    @Override
    public ServiceResult withdraw(String userId, String password) {

        Optional<Member> optionalMember = memberRepository.findById(userId);
        if(!optionalMember.isPresent()) {
            return new ServiceResult(false,"회원정보가 존재하지 않습니다.");
        }

        Member member = optionalMember.get();


        if(!PasswordUtils.equals(password, member.getPassword())){
            return new ServiceResult(false,"비밀번호가 일치하지 않습니다.");
        }

        member.setUserName("삭제회원");
        member.setPhone("");
        member.setPassword("");
        member.setRegDt(null);
        member.setUdtDt(null);
        member.setEmailAuthYn(false);
        member.setEmailAuthDt(null);
        member.setEmailAuthKey("");
        member.setResetPasswordKey("");
        member.setResetPasswordLimitDt(null);
        member.setUserStatus(Member.MEMBER_STATUS_WITHDRAW);
        member.setZipcode("");
        member.setAddr("");
        member.setAddrDetail("");
        memberRepository.save(member);

        return new ServiceResult();
    }


    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        Optional<Member> optionalMember = memberRepository.findById(userId);
        if(!optionalMember.isPresent()) {
            throw new UsernameNotFoundException("회원 정보가 존재하지 않습니다.");
        }

        Member member = optionalMember.get();

        if(Member.MEMBER_STATUS_REQ.equals(member.getUserStatus())){
            throw new MemberNotEmailAuthException(" 이메일 인증을 해야 로그인이 가능합니다. ");
        }

        if(Member.MEMBER_STATUS_STOP.equals(member.getUserStatus())){
            throw new MemberStopUserException("정지된 회원 입니다.");
        }

        if(Member.MEMBER_STATUS_WITHDRAW.equals(member.getUserStatus())){
            throw new MemberStopUserException("탈퇴된 회원 입니다.");
        }

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        if(member.isAdminYn()){
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

        return new User(member.getUserId(), member.getPassword(), grantedAuthorities);
    }
}
