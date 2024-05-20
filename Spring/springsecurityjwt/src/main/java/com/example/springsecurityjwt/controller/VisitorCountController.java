package com.example.springsecurityjwt.controller;

import com.example.springsecurityjwt.entity.Visitor;
import com.example.springsecurityjwt.repository.VisitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VisitorCountController {

    @Autowired
    private VisitorRepository visitorRepository;

    /*
    @GetMapping("/visitorCount")
    @ResponseBody
    public int getVisitorCount() {
        Visitor visitor = visitorRepository.findById(1L).orElse(new Visitor());
        int count = visitor.getCount();
        visitor.setCount(count + 1);
        visitorRepository.save(visitor);
        return count + 1;
    }
    */
    
    @GetMapping("/visitorCount")
    public String getVisitorCount(Model model) {
        Visitor visitor = visitorRepository.findById(1L).orElse(new Visitor());
        int count = visitor.getCount();
        visitor.setCount(count + 1);
        visitorRepository.save(visitor);
        
        // HTML에 표시할 방문자 수를 모델에 추가합니다.
        model.addAttribute("visitorCount", count);
        
        // 방문자 수를 표시할 HTML 파일의 이름을 반환합니다.
        return "visitorCount";
    }
}
