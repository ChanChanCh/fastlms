package com.chanproject.fastlms.member.repository;


import com.chanproject.fastlms.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
}
