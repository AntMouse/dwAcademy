package com.example.springsecurityjwt.controller;

import com.example.springsecurityjwt.dto.DinosaurDto;
import com.example.springsecurityjwt.entity.Dinosaur;
import com.example.springsecurityjwt.repository.DinosaurRepository;
import com.example.springsecurityjwt.service.DinosaurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class DinosaursController {


    // 공룡 관리 html을 다루는 컨트롤러

    private final DinosaurRepository dinosaurRepository;
    private final DinosaurService dinosaurService;

    @Autowired
    public DinosaursController(DinosaurRepository dinosaurRepository, DinosaurService dinosaurService) {
        this.dinosaurRepository = dinosaurRepository;
        this.dinosaurService = dinosaurService;
    }

    @GetMapping("/dinosaurs")
    public String getAllDinosaurs(Model model) {
        Iterable<Dinosaur> dinosaurs = dinosaurRepository.findAll();
        model.addAttribute("dinosaurs", dinosaurs);
        return "dinosaurs";
    }


    /* 공룡 추가 뷰 */
    @GetMapping("/addDinosaur")
    public String addDinosaurView() {
        return "addDinosaur";
    }

    /* 공룡 추가 API */
    @PostMapping("/api/addDinosaur")
    public String addDinosaur(DinosaurDto dinosaurDto, RedirectAttributes redirectAttributes) {
        try {
            dinosaurService.addDinosaur(dinosaurDto);
            return "redirect:/dinosaurs"; // 공룡 추가 성공 시 공룡 목록 페이지로 리다이렉트
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "공룡 추가 중 오류가 발생했습니다. 다시 시도해주세요.");
            return "addDinosaur"; // 공룡 추가 실패 시 공룡 추가 페이지로 리다이렉트
        }
    }

    @PostMapping("/dinosaurs/delete")
    public String deleteDinosaur(@RequestParam Long id) {
        // 공룡 삭제 로직을 구현합니다.
        dinosaurService.deleteById(id);

        // 삭제 후 리다이렉트할 페이지를 지정합니다.
        return "redirect:/dinosaurs";
    }



    @GetMapping("/dinosaur/edit/{id}")
    public String editDinosaur(@PathVariable("id") Long id, Model model) {
        // 공룡 정보를 데이터베이스에서 가져옴
        Dinosaur dinosaur = dinosaurService.findById(id);

        // 가져온 공룡 정보를 모델에 추가하여 Thymeleaf로 전달
        model.addAttribute("dinosaur", dinosaur);

        return "editDinosaur"; // 수정할 공룡 정보를 담은 페이지로 이동
    }


    // 수정된 공룡 정보를 저장하는 핸들러
    @PostMapping("/dinosaur/edit")
    public String saveEditedDinosaur(@ModelAttribute Dinosaur dinosaur) {
        // 수정된 공룡 정보를 데이터베이스에 저장
        dinosaurService.save(dinosaur);

        // 수정 후에는 보통 상세 페이지로 리다이렉트하거나, 수정된 내용을 보여주는 페이지로 이동합니다.
        return "redirect:/dinosaurs"; // 수정된 공룡 정보를 보여주는 페이지로 리다이렉트
    }




}







