package com.example.springsecurityjwt.service;

import com.example.springsecurityjwt.dto.DinosaurDto;
import com.example.springsecurityjwt.entity.Dinosaur;
import com.example.springsecurityjwt.repository.DinosaurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DinosaurService {

    private final DinosaurRepository dinosaurRepository;

    @Autowired
    public DinosaurService(DinosaurRepository dinosaurRepository) {
        this.dinosaurRepository = dinosaurRepository;
    }

    public void addDinosaur(DinosaurDto dinosaurDto) {
        // DinosaurDto 객체를 Dinosaur 엔티티로 변환
        Dinosaur dinosaur = new Dinosaur();
        dinosaur.setDinoName(dinosaurDto.getDinoName());
        dinosaur.setDinoAge(dinosaurDto.getDinoAge());
        dinosaur.setDinoWeight(dinosaurDto.getDinoWeight());
        dinosaur.setDinoDiet(dinosaurDto.getDinoDiet());
        dinosaur.setDinoSpecies(dinosaurDto.getDinoSpecies());

        // 리포지토리를 사용하여 데이터베이스에 공룡 정보 저장
        dinosaurRepository.save(dinosaur);
    }



    // CRUD repository에 저장되어있는 deleteById 메소드 사용
    public void deleteById(Long id) {
        dinosaurRepository.deleteById(id);
    }

    //findById를 이용하여 해당 수정 id 페이지로 이동하기
    public Dinosaur findById(Long id) {
        return dinosaurRepository.findById(id).orElse(null);
    }

    // 수정완료
    public Dinosaur save(Dinosaur dinosaur) {
        return dinosaurRepository.save(dinosaur);
    }





}