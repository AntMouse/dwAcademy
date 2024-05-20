package com.example.springsecurityjwt.controller;

import com.example.springsecurityjwt.entity.Dinosaur;
import com.example.springsecurityjwt.repository.DinosaurRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class DinosaurController {
    private final DinosaurRepository dinosaurRepository;
    public DinosaurController(DinosaurRepository dinosaurRepository) {
        this.dinosaurRepository = dinosaurRepository;
    }

    @GetMapping("/Mosasaurus")
    public String getMosasaurus(Model model) {
        Dinosaur mosa1 = dinosaurRepository.findById(1L).orElse(null);
        Dinosaur mosa2 = dinosaurRepository.findById(2L).orElse(null);
        Dinosaur mosa3 = dinosaurRepository.findById(3L).orElse(null);
        Dinosaur mosa4 = dinosaurRepository.findById(4L).orElse(null);
        model.addAttribute("mosa1", mosa1); // 공룡 정보를 Model에 추가
        model.addAttribute("mosa2", mosa2);
        model.addAttribute("mosa3", mosa3);
        model.addAttribute("mosa4", mosa4);
        return "Mosasaurus";
    }

    @GetMapping("/Pteranodon")
    public String getPteranodon(Model model) {
        Dinosaur ptera1 = dinosaurRepository.findById(5L).orElse(null);
        Dinosaur ptera2 = dinosaurRepository.findById(6L).orElse(null);
        Dinosaur ptera3 = dinosaurRepository.findById(7L).orElse(null);
        Dinosaur ptera4 = dinosaurRepository.findById(8L).orElse(null);
        model.addAttribute("ptera1", ptera1);
        model.addAttribute("ptera2", ptera2);
        model.addAttribute("ptera3", ptera3);
        model.addAttribute("ptera4", ptera4);
        return "Pteranodon";
    }

    @GetMapping("/Spinosaurus")
    public String getSpinosaurus(Model model) {
        Dinosaur spino1 = dinosaurRepository.findById(9L).orElse(null);
        Dinosaur spino2 = dinosaurRepository.findById(10L).orElse(null);
        Dinosaur spino3 = dinosaurRepository.findById(11L).orElse(null);
        Dinosaur spino4 = dinosaurRepository.findById(12L).orElse(null);
        model.addAttribute("spino1", spino1);
        model.addAttribute("spino2", spino2);
        model.addAttribute("spino3", spino3);
        model.addAttribute("spino4", spino4);
        return "Spinosaurus";
    }

    @GetMapping("/Triceratops")
    public String getTriceratops(Model model) {
        Dinosaur trice1 = dinosaurRepository.findById(13L).orElse(null);
        Dinosaur trice2 = dinosaurRepository.findById(14L).orElse(null);
        Dinosaur trice3 = dinosaurRepository.findById(15L).orElse(null);
        Dinosaur trice4 = dinosaurRepository.findById(16L).orElse(null);
        model.addAttribute("trice1", trice1);
        model.addAttribute("trice2", trice2);
        model.addAttribute("trice3", trice3);
        model.addAttribute("trice4", trice4);
        return "Triceratops";
    }

    @GetMapping("/Tyrannosaurus")
    public String getTyrannosaurus(Model model) {
        Dinosaur tino1 = dinosaurRepository.findById(17L).orElse(null);
        Dinosaur tino2 = dinosaurRepository.findById(18L).orElse(null);
        Dinosaur tino3 = dinosaurRepository.findById(19L).orElse(null);
        Dinosaur tino4 = dinosaurRepository.findById(20L).orElse(null);
        model.addAttribute("tino1", tino1);
        model.addAttribute("tino2", tino2);
        model.addAttribute("tino3", tino3);
        model.addAttribute("tino4", tino4);
        return "Tyrannosaurus";
    }


}
