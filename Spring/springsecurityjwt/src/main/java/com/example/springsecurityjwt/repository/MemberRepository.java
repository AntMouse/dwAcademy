package com.example.springsecurityjwt.repository;


import com.example.springsecurityjwt.entity.Member;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface MemberRepository extends CrudRepository<Member, Long> {

    // memberName이 중복확인하는 메소드
    Optional<Member> findByMemberName(String memberName);
}
