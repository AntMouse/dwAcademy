package com.example.springsecurityjwt.service;


import com.example.springsecurityjwt.entity.Dinosaur;
import com.example.springsecurityjwt.repository.MemberRepository;
import com.example.springsecurityjwt.security.UserRoleEnum;
import com.example.springsecurityjwt.dto.LoginRequestDto;
import com.example.springsecurityjwt.dto.SignUpRequestDto;
import com.example.springsecurityjwt.entity.Member;
import com.example.springsecurityjwt.jwt.JwtUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MemberService {
    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;



    public MemberService(MemberRepository memberRepository, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        super();
        this.memberRepository = memberRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    /*회원 가입*/
    @Transactional
    public void signUp(SignUpRequestDto requestDto) {
        String memberName = requestDto.getMemberName();
        String name = requestDto.getName();
        String email = requestDto.getEmail();
        int age = Integer.parseInt(requestDto.getAge());
        int career = Integer.parseInt(requestDto.getCareer());
        int salary = Integer.parseInt(requestDto.getSalary());
        int wage = Integer.parseInt(requestDto.getWage());
        int day = Integer.parseInt(requestDto.getDay());
        int incentive = Integer.parseInt(requestDto.getIncentive());

        // 이미 존재하는 회원 이름인지 확인
        if (memberRepository.findByMemberName(memberName).isPresent()) {

            throw new IllegalArgumentException("이미 존재하는 회원 이름입니다: " + memberName);
        }
        String morningTask = requestDto.getMorningTask();
        String afternoonTask = requestDto.getAfternoonTask();

        String password = passwordEncoder.encode(requestDto.getPassword());
        UserRoleEnum role = UserRoleEnum.valueOf(requestDto.getRole());

        Member member = new Member(memberName, password, role, name, email, age, career, salary,morningTask, afternoonTask, wage, day, incentive);
        memberRepository.save(member);
    }

    /*로그인*/
    @Transactional
    public void login(LoginRequestDto requestDto, HttpServletResponse response) {
        Optional<Member> optionalMember = memberRepository.findByMemberName(requestDto.getMemberName());

        if (optionalMember.isEmpty()) {

            throw new IllegalArgumentException("회원이 존재하지 않음");
        }

        Member member = optionalMember.get();

        if (!passwordEncoder.matches(requestDto.getPassword(), member.getPassword())) {

            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        Cookie cookie = new Cookie(JwtUtil.AUTHORIZATION_HEADER,
                jwtUtil.createToken(member.getMemberName(), member.getRole()));
        cookie.setMaxAge(7 * 24 * 60 * 60); // 7일 동안 유효
        cookie.setPath("/");
        cookie.setDomain("localhost");
        cookie.setSecure(false);

        response.addCookie(cookie);
    }

    public void deleteById(Long id) {
        memberRepository.deleteById(id);
    }
    public Member findById(Long id) {
        return memberRepository.findById(id).orElse(null);
    }
    public Member save(Member member) {
        String encodedPassword = passwordEncoder.encode(member.getPassword());
        member.setPassword(encodedPassword);

        return memberRepository.save(member);
    }



    @Transactional
    public void updateTaskForMemberWithId(long id) {
        Member member = memberRepository.findById(id).orElse(null);
        LocalDate today = LocalDate.now();
        if (today.getDayOfWeek() == DayOfWeek.MONDAY){
            if (member != null) {
                // 각 회원에 대한 작업을 수행
                if (id == 1) {
                    member.setMorningTask("직원 관리");
                    member.setAfternoonTask("휴식");
                } else if (id == 2) {
                    member.setMorningTask("티라노사우루스 관리");
                    member.setAfternoonTask("스피노사우루스 관리");
                } else if (id == 3) {
                    member.setMorningTask("티라노사우루스 치료");
                    member.setAfternoonTask("프테라노돈 새끼 치료");
                } else if (id == 4) {
                    member.setMorningTask("휴식");
                    member.setAfternoonTask("스테고사우루스 치료");
                } else if (id == 5) {
                    member.setMorningTask("모사사우루스 울타리 안전점검");
                    member.setAfternoonTask("프테라노돈 새장 안전점검");
                } else if (id == 6) {
                    member.setMorningTask("티라노사우루스 울타리 안전점검");
                    member.setAfternoonTask("스피노사우루스 울타리 안전점검");
                } else if (id == 7) {
                    member.setMorningTask("티라노사우루스 가이드");
                    member.setAfternoonTask("모사사우루스 가이드");
                } else if (id == 8) {
                    member.setMorningTask("프테라노돈 가이드");
                    member.setAfternoonTask("스테고사우루스 가이드");
                } else if (id == 9) {
                    member.setMorningTask("티라노사우루스 새끼 브리딩");
                    member.setAfternoonTask("스테고사우루스 새끼 브리딩");
                } else if (id == 10) {
                    member.setMorningTask("모사사우루스 새끼 브리딩");
                    member.setAfternoonTask("스피노사우루스 새끼 브리딩");
                }
                // 나머지 회원에 대한 작업도 동일한 방식으로 추가 가능
                memberRepository.save(member);
            }
        } else if (today.getDayOfWeek() == DayOfWeek.TUESDAY){
            if (member != null) {
                // 각 회원에 대한 작업을 수행
                if (id == 1) {
                    member.setMorningTask("공원 관리");
                    member.setAfternoonTask("휴식");
                } else if (id == 2) {
                    member.setMorningTask("스피노사우루스 관리");
                    member.setAfternoonTask("모사사우루스 관리");
                } else if (id == 3) {
                    member.setMorningTask("프테라노돈 치료");
                    member.setAfternoonTask("모사사우루스 새끼 치료");
                } else if (id == 4) {
                    member.setMorningTask("스테고사우루스 치료");
                    member.setAfternoonTask("스피노사우루스 치료");
                } else if (id == 5) {
                    member.setMorningTask("프테라노돈 울타리 안전점검");
                    member.setAfternoonTask("모사사우루스 울타리 안전점검");
                } else if (id == 6) {
                    member.setMorningTask("스피노사우루스 울타리 안전점검");
                    member.setAfternoonTask("스테고사우루스 울타리 안전점검");
                } else if (id == 7) {
                    member.setMorningTask("모사사우르스 가이드");
                    member.setAfternoonTask("스피노사우루스 가이드");
                } else if (id == 8) {
                    member.setMorningTask("스테고사우루스 가이드");
                    member.setAfternoonTask("모사사우루스 가이드");
                } else if (id == 9) {
                    member.setMorningTask("스피노사우루스 새끼 브리딩");
                    member.setAfternoonTask("티라노사우루스 새끼 브리딩");
                } else if (id == 10) {
                    member.setMorningTask("스피노사우루스 새끼 브리딩");
                    member.setAfternoonTask("모사사우루스 새끼 브리딩");
                }
                // 나머지 회원에 대한 작업도 동일한 방식으로 추가 가능
                memberRepository.save(member);
            }
        } else if (today.getDayOfWeek() == DayOfWeek.WEDNESDAY){
            if (member != null) {
                // 각 회원에 대한 작업을 수행
                if (id == 1) {
                    member.setMorningTask("직원 관리");
                    member.setAfternoonTask("휴식");
                } else if (id == 2) {
                    member.setMorningTask("모사사우루스 관리");
                    member.setAfternoonTask("프테라노돈 관리");
                } else if (id == 3) {
                    member.setMorningTask("모사사우루스 치료");
                    member.setAfternoonTask("스테고사우루스 새끼 치료");
                } else if (id == 4) {
                    member.setMorningTask("스피노사우루스 치료");
                    member.setAfternoonTask("프테라노돈 치료");
                } else if (id == 5) {
                    member.setMorningTask("티라노사우루스 울타리 안전점검");
                    member.setAfternoonTask("스피노사우루스 울타리 안전점검");
                } else if (id == 6) {
                    member.setMorningTask("스테고사우루스 울타리 안전점검");
                    member.setAfternoonTask("모사사우르스 울타리 안전점검");
                } else if (id == 7) {
                    member.setMorningTask("티라노사우루스 가이드");
                    member.setAfternoonTask("프테라노돈 가이드");
                } else if (id == 8) {
                    member.setMorningTask("모사사우루스 가이드");
                    member.setAfternoonTask("스피노사우루스 가이드");
                } else if (id == 9) {
                    member.setMorningTask("티라노사우루스 새끼 브리딩");
                    member.setAfternoonTask("스피노사우루스 새끼 브리딩");
                } else if (id == 10) {
                    member.setMorningTask("스피노사우루스 새끼 브리딩");
                    member.setAfternoonTask("프테라노돈 새끼 브리딩");
                }
                // 나머지 회원에 대한 작업도 동일한 방식으로 추가 가능
                memberRepository.save(member);
            }
        } else if (today.getDayOfWeek() == DayOfWeek.THURSDAY){
            if (member != null) {
                // 각 회원에 대한 작업을 수행
                if (id == 1) {
                    member.setMorningTask("공룡 관리");
                    member.setAfternoonTask("휴식");
                } else if (id == 2) {
                    member.setMorningTask("프테라노돈 관리");
                    member.setAfternoonTask("모사사우루스 관리");
                } else if (id == 3) {
                    member.setMorningTask("스테고사우루스 치료");
                    member.setAfternoonTask("스피노사우루스 새끼 치료");
                } else if (id == 4) {
                    member.setMorningTask("프테라노돈 치료");
                    member.setAfternoonTask("티라노사우루스 치료");
                } else if (id == 5) {
                    member.setMorningTask("스피노사우루스 울타리 안전점검");
                    member.setAfternoonTask("스테고사우루스 울타리 안전점검");
                } else if (id == 6) {
                    member.setMorningTask("모사사우르스 울타리 안전점검");
                    member.setAfternoonTask("티라노사우루스 울타리 안전점검");
                } else if (id == 7) {
                    member.setMorningTask("프테라노돈 가이드");
                    member.setAfternoonTask("모사사우루스 가이드");
                } else if (id == 8) {
                    member.setMorningTask("스피노사우루스 가이드");
                    member.setAfternoonTask("티라노사우루스 가이드");
                } else if (id == 9) {
                    member.setMorningTask("스피노사우루스 새끼 브리딩");
                    member.setAfternoonTask("스피노사우루스 새끼 브리딩");
                } else if (id == 10) {
                    member.setMorningTask("프테라노돈 새끼 브리딩");
                    member.setAfternoonTask("티라노사우루스 새끼 브리딩");
                }
                // 나머지 회원에 대한 작업도 동일한 방식으로 추가 가능
                memberRepository.save(member);
            }
        } else if (today.getDayOfWeek() == DayOfWeek.FRIDAY){
            if (member != null) {
                // 각 회원에 대한 작업을 수행
                if (id == 1) {
                    member.setMorningTask("직원 관리");
                    member.setAfternoonTask("휴식");
                } else if (id == 2) {
                    member.setMorningTask("모사사우루스 관리");
                    member.setAfternoonTask("스테고사우루스 관리");
                } else if (id == 3) {
                    member.setMorningTask("스피노사우루스 치료");
                    member.setAfternoonTask("프테라노돈 새끼 치료");
                } else if (id == 4) {
                    member.setMorningTask("티라노사우루스 치료");
                    member.setAfternoonTask("스피노사우루스 치료");
                } else if (id == 5) {
                    member.setMorningTask("스테고사우루스 울타리 안전점검");
                    member.setAfternoonTask("모사사우르스 울타리 안전점검");
                } else if (id == 6) {
                    member.setMorningTask("티라노사우루스 울타리 안전점검");
                    member.setAfternoonTask("프테라노돈 울타리 안전점검");
                } else if (id == 7) {
                    member.setMorningTask("모사사우루스 가이드");
                    member.setAfternoonTask("스피노사우루스 가이드");
                } else if (id == 8) {
                    member.setMorningTask("티라노사우루스 가이드");
                    member.setAfternoonTask("스피노사우루스 가이드");
                } else if (id == 9) {
                    member.setMorningTask("스피노사우루스 새끼 브리딩");
                    member.setAfternoonTask("프테라노돈 새끼 브리딩");
                } else if (id == 10) {
                    member.setMorningTask("티라노사우루스 새끼 브리딩");
                    member.setAfternoonTask("스테고사우루스 새끼 브리딩");
                }
                // 나머지 회원에 대한 작업도 동일한 방식으로 추가 가능
                memberRepository.save(member);
            }
        } else if (today.getDayOfWeek() == DayOfWeek.SATURDAY){
            if (member != null) {
                // 각 회원에 대한 작업을 수행
                if (id == 1) {
                    member.setMorningTask("공룡 관리");
                    member.setAfternoonTask("휴식");
                } else if (id == 2) {
                    member.setMorningTask("스테고사우루스 관리");
                    member.setAfternoonTask("스피노사우루스 관리");
                } else if (id == 3) {
                    member.setMorningTask("프테라노돈 치료");
                    member.setAfternoonTask("티라노사우루스 새끼 치료");
                } else if (id == 4) {
                    member.setMorningTask("스피노사우루스 치료");
                    member.setAfternoonTask("스테고사우루스 치료");
                } else if (id == 5) {
                    member.setMorningTask("모사사우르스 울타리 안전점검");
                    member.setAfternoonTask("티라노사우루스 울타리 안전점검");
                } else if (id == 6) {
                    member.setMorningTask("프테라노돈 울타리 안전점검");
                    member.setAfternoonTask("모사사우루스 울타리 안전점검");
                } else if (id == 7) {
                    member.setMorningTask("스피노사우루스 가이드");
                    member.setAfternoonTask("티라노사우루스 가이드");
                } else if (id == 8) {
                    member.setMorningTask("스피노사우루스 가이드");
                    member.setAfternoonTask("모사사우루스 가이드");
                } else if (id == 9) {
                    member.setMorningTask("프테라노돈 새끼 브리딩");
                    member.setAfternoonTask("티라노사우루스 새끼 브리딩");
                } else if (id == 10) {
                    member.setMorningTask("스테고사우루스 새끼 브리딩");
                    member.setAfternoonTask("스피노사우루스 새끼 브리딩");
                }
                // 나머지 회원에 대한 작업도 동일한 방식으로 추가 가능
                memberRepository.save(member);
            }
        } else if (today.getDayOfWeek() == DayOfWeek.SATURDAY){
            if (member != null) {
                // 각 회원에 대한 작업을 수행
                if (id == 1) {
                    member.setMorningTask("공룡 관리");
                    member.setAfternoonTask("퇴근");
                } else if (id == 2) {
                    member.setMorningTask("스피노사우루스 관리");
                    member.setAfternoonTask("퇴근");
                } else if (id == 3) {
                    member.setMorningTask("티라노사우루스 치료");
                    member.setAfternoonTask("퇴근");
                } else if (id == 4) {
                    member.setMorningTask("스테고사우루스 치료");
                    member.setAfternoonTask("퇴근");
                } else if (id == 5) {
                    member.setMorningTask("티라노사우루스 울타리 안전점검");
                    member.setAfternoonTask("퇴근");
                } else if (id == 6) {
                    member.setMorningTask("모사사우루스 울타리 안전점검");
                    member.setAfternoonTask("퇴근");
                } else if (id == 7) {
                    member.setMorningTask("티라노사우루스 가이드");
                    member.setAfternoonTask("퇴근");
                } else if (id == 8) {
                    member.setMorningTask("모사사우루스 가이드");
                    member.setAfternoonTask("퇴근");
                } else if (id == 9) {
                    member.setMorningTask("티라노사우루스 새끼 브리딩");
                    member.setAfternoonTask("퇴근");
                } else if (id == 10) {
                    member.setMorningTask("스피노사우루스 새끼 브리딩");
                    member.setAfternoonTask("퇴근");
                }
                // 나머지 회원에 대한 작업도 동일한 방식으로 추가 가능
                memberRepository.save(member);
            }
        }

    }

    // @Scheduled 어노테이션을 사용하여 매일 자정에 실행되도록 설정
    @Scheduled(cron = "0 0 0 * * *")
    @Transactional
    public void updateTasks() {
        List<Long> memberIds = Arrays.asList(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L);
        for (Long memberId : memberIds) {
            updateTaskForMemberWithId(memberId);
        }
    }


    // 매월 말 자정이 실행되도록 설정
    @Scheduled(cron = "0 0 0 L * ?")
    // @Scheduled(cron = "15 16 15 4 4 ?") // 테스트용(초, 분, 시, 일, 월)
    public void calculateMonthlySalaryAutomatically() {
        Iterable<Member> members = memberRepository.findAll();
        for (Member member : members) {
            calculateEmployeeSalary(member);
            memberRepository.save(member); // 변경된 정보를 데이터베이스에 저장
        }
    }

    // 매월 말에 직원 급여를 계산하는 메서드
    private void calculateEmployeeSalary(Member member) {
        LocalDate currentDate = LocalDate.now();
        int lastDayOfMonth = currentDate.lengthOfMonth(); // 현재 월의 마지막 날짜
        int currentDay = currentDate.getDayOfMonth(); // 현재 날짜의 일

        // 테스트
        lastDayOfMonth = 4;

        if (currentDay == lastDayOfMonth) {
            // 매월 말일이면 직원의 월급을 계산
            member.salaye();
        } else {
            // 매월 말일이 아니면 월급(salary) 0으로 변경.
            member.setSalary(0);
        }
    }



}
