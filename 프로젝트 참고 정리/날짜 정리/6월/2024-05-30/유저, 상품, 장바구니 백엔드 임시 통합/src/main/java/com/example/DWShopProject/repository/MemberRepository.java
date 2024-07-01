package com.example.DWShopProject.repository;

import com.example.DWShopProject.entity.Member;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MemberRepository extends CrudRepository<Member, Long> {
    Optional<Member> findByMemberId(String memberId);
}
