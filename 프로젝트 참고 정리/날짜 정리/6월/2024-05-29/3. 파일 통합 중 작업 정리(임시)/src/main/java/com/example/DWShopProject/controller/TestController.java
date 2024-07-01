package com.example.DWShopProject.controller;

import com.example.DWShopProject.entity.User;
import com.example.DWShopProject.service.CardService;
import com.example.DWShopProject.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class TestController {

    @Autowired
    CardService cardService;

    @Autowired
    UserService userService;
    @GetMapping("/user/card/{id}")
    public String main(@PathVariable("id") Long id, Model model
    ) {

        User user = userService.findById(id);

        model.addAttribute("user", user);

        return "edit";
    }

    @PostMapping("/card/{id}")
    public String saveCard(@PathVariable Long id,
                           @RequestParam String cardnumber,
                           @RequestParam String password,
                           Model model) {

        log.info(cardnumber);
        log.info(password);

        User user = cardService.SaveCard(id,cardnumber,password);

        model.addAttribute("user", user);

        return "redirect:/users";
    }



}
