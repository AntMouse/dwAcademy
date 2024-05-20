package com.example.springsecurityjwt;

import com.example.springsecurityjwt.entity.Dinosaur;
import com.example.springsecurityjwt.entity.Member;
import com.example.springsecurityjwt.repository.DinosaurRepository;
import com.example.springsecurityjwt.repository.MemberRepository;
import com.example.springsecurityjwt.security.UserRoleEnum;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringsecurityjwtApplication implements CommandLineRunner {

    private final DinosaurRepository dinosaurRepository;
    private final MemberRepository userEntityRepository;



    private BCryptPasswordEncoder bcryptPasswordEncoder;

    public SpringsecurityjwtApplication(DinosaurRepository dinosaurRepository, MemberRepository userEntityRepository) {
        super();
        this.dinosaurRepository = dinosaurRepository;
        this.userEntityRepository = userEntityRepository;

    }

    public static void main(String[] args) {
        SpringApplication.run(SpringsecurityjwtApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        int a = 5;
        //모사사우루스 1 ~ 4
        dinosaurRepository.save(new Dinosaur("Tom", "모사사우루스", 10, 500, "Seafood"));
        dinosaurRepository.save(new Dinosaur("Lily", "모사사우루스", 8, 480, "Seafood"));
        dinosaurRepository.save(new Dinosaur("Max", "모사사우루스", 12, 550,"Seafood"));
        dinosaurRepository.save(new Dinosaur("Daisy", "모사사우루스", 5, 300,"Seafood"));

        //프테라노돈 1 ~ 4
        dinosaurRepository.save(new Dinosaur("Alex", "프레라노돈", 5, 20, "Carnivore"));
        dinosaurRepository.save(new Dinosaur("Sophie", "프레라노돈", 3, 18, "Carnivore"));
        dinosaurRepository.save(new Dinosaur("Victor", "프레라노돈", 6, 25, "Carnivore"));
        dinosaurRepository.save(new Dinosaur("Ava", "프레라노돈", 4, 22, "Carnivore"));

        //스피노사우루스 1 ~ 4
        dinosaurRepository.save(new Dinosaur("Jack", "스피노사우루스", 15, 2000, "Carnivore"));
        dinosaurRepository.save(new Dinosaur("Luna", "스피노사우루스", 13, 1800, "Carnivore"));
        dinosaurRepository.save(new Dinosaur("Bruno", "스피노사우루스", 17, 2200, "Carnivore"));
        dinosaurRepository.save(new Dinosaur("Roxy", "스피노사우루스", 10, 1500, "Carnivore"));

        //트리케라톱스 1 ~ 4
        dinosaurRepository.save(new Dinosaur("Spike", "트리케라톱스", 8, 500, "Herbivore"));
        dinosaurRepository.save(new Dinosaur("Rose", "트리케라톱스", 7, 480, "Herbivore"));
        dinosaurRepository.save(new Dinosaur("Rocky", "트리케라톱스", 4, 300, "Herbivore"));
        dinosaurRepository.save(new Dinosaur("Rex", "트리케라톱스", 12, 700, "Herbivore"));

        //티라노사우루스 1 ~ 4
        dinosaurRepository.save(new Dinosaur("Terry", "티라노사우루스", 20, 8000, "Carnivore"));
        dinosaurRepository.save(new Dinosaur("Sally", "티라노사우루스", 18, 7500, "Carnivore"));
        dinosaurRepository.save(new Dinosaur("Buster", "티라노사우루스", 6, 1500, "Carnivore"));
        dinosaurRepository.save(new Dinosaur("Lucy", "티라노사우루스", 25, 9000, "Carnivore"));

        //공원 관리자 1 ~ 2
        userEntityRepository.save(new Member("id01", "$2a$12$xaaPMSLW7gCN/KQxlaxdoegYbQWC3x0r/EV1yy1Jq/QEECUplEP3u", UserRoleEnum.PARKMANAGER, "앨리스", "Alice@MesozoicEden.com", 30, 8, 0, "공원 관리", "휴식", 0, 0, 0));
        userEntityRepository.save(new Member("id02", "$2a$12$xaaPMSLW7gCN/KQxlaxdoegYbQWC3x0r/EV1yy1Jq/QEECUplEP3u", UserRoleEnum.PARKMANAGER, "파우스트", "Faust@MesozoicEden.com", 28, 6, 0 , "티라노사우루스 관리", "모사사우루스 관리", 0, 0, 0));

        //공원 수의사 1 ~ 2
        userEntityRepository.save(new Member("id03", "$2a$12$xaaPMSLW7gCN/KQxlaxdoegYbQWC3x0r/EV1yy1Jq/QEECUplEP3u", UserRoleEnum.VETERINARIAN, "그레고르", "Gregor@MesozoicEden.com", 33, 10, 0, "스테고사우루스 치료", "스피노사우루스 치료", 0, 0, 0));
        userEntityRepository.save(new Member("id04", "$2a$12$xaaPMSLW7gCN/KQxlaxdoegYbQWC3x0r/EV1yy1Jq/QEECUplEP3u", UserRoleEnum.VETERINARIAN, "체자렛", "Cesare@MesozoicEden.com", 35, 10, 0, "프테라노돈 치료", "티라노사우루스 치료", 0, 0, 0));

        //공원 경비대 1 ~ 2
        userEntityRepository.save(new Member("id05", "$2a$12$xaaPMSLW7gCN/KQxlaxdoegYbQWC3x0r/EV1yy1Jq/QEECUplEP3u", UserRoleEnum.SECURITYOFFICER, "이스마엘", "Ishmael@MesozoicEden.com", 32, 7, 0, "모사사우루스 울타리 점검", "스테고사우루스 울타리 점검", 0, 0, 0));
        userEntityRepository.save(new Member("id06", "$2a$12$xaaPMSLW7gCN/KQxlaxdoegYbQWC3x0r/EV1yy1Jq/QEECUplEP3u", UserRoleEnum.SECURITYOFFICER, "오티스", "Outis@MesozoicEden.com", 28, 5, 0, "스피노사우루스 울타리 점검", "프테라노돈 울타리 점검", 0, 0, 0));

        //공원 가이드 1 ~ 2
        userEntityRepository.save(new Member("id07", "$2a$12$xaaPMSLW7gCN/KQxlaxdoegYbQWC3x0r/EV1yy1Jq/QEECUplEP3u", UserRoleEnum.GUIDE, "로쟈", "Roja@MesozoicEden.com", 31, 4, 0, "티라노사우루스 가이드", "모사사우루스 가이드", 0, 0, 0));
        userEntityRepository.save(new Member("id08", "$2a$12$xaaPMSLW7gCN/KQxlaxdoegYbQWC3x0r/EV1yy1Jq/QEECUplEP3u", UserRoleEnum.GUIDE, "마이클", "Michael@MesozoicEden.com", 28, 5, 0, "스테고사우루스 가이드", "스피노사우루스 가이드", 0, 0, 0));

        //공원 사육사 1 ~ 2
        userEntityRepository.save(new Member("id09", "$2a$12$xaaPMSLW7gCN/KQxlaxdoegYbQWC3x0r/EV1yy1Jq/QEECUplEP3u", UserRoleEnum.ZOOKEEPER, "아드리카", "Adrika@MesozoicEden.com", 47, 21, 0, "프테라노돈 브리딩", "티라노사우루스 브리딩", 0, 0, 0));
        userEntityRepository.save(new Member("id10", "$2a$12$xaaPMSLW7gCN/KQxlaxdoegYbQWC3x0r/EV1yy1Jq/QEECUplEP3u", UserRoleEnum.ZOOKEEPER, "뫼르소", "Morso@MesozoicEden.com", 33, 3, 0, "모사사우루스 브리딩", "스테고사우루스 브리딩", 0, 0, 0));


    }
}
