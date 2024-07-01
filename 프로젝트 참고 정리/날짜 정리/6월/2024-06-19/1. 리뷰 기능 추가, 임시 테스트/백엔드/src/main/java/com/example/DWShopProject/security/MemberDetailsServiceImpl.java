package com.example.DWShopProject.security;

import com.example.DWShopProject.entity.Member;
import com.example.DWShopProject.repository.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service // 서비스 클래스임을 나타냄
public class MemberDetailsServiceImpl implements UserDetailsService {
    private final MemberRepository memberRepository;

    // 생성자를 통해 MemberRepository 주입
    public MemberDetailsServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
        // memberId를 이용해 회원 정보 조회
        Member member = memberRepository.findByMemberId(memberId)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
        // 조회된 회원 정보를 기반으로 UserDetails 객체 반환
        return new MemberDetailsImpl(member, member.getPassword(), member.getMemberId());
    }
}
