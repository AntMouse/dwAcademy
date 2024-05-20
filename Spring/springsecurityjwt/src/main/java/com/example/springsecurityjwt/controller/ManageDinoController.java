package com.example.springsecurityjwt.controller;

import com.example.springsecurityjwt.recode.Food;
import com.example.springsecurityjwt.recode.Health;
import com.example.springsecurityjwt.recode.ParkSafe;
import com.example.springsecurityjwt.service.FoodService;
import com.example.springsecurityjwt.service.HealthService;
import com.example.springsecurityjwt.service.ParkSafeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class ManageDinoController {
	private final HealthService healthService;
	private final FoodService foodService;
	private final ParkSafeService parkService;
	public ManageDinoController(HealthService healthService, FoodService foodService, ParkSafeService parkService) {
		this.healthService = healthService;
		this.foodService = foodService;
		this.parkService = parkService;
	}
	
	@GetMapping("/ManageDino")
	public String ManageDino(Model model) {
		model.addAttribute("healths", healthService.getHealths());
		model.addAttribute("foods", foodService.getFoods());
		model.addAttribute("parksafes", parkService.getSafes());
		
		LocalDateTime ldt = LocalDateTime.of(2024, 1, 1, 12, 0);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
		model.addAttribute("ldt", ldt.format(dtf));
		return "ManageDino";
	}
	
	@PostMapping("/new-health")
	public String newHealth(@ModelAttribute Health newHealth) {
		healthService.create(newHealth);
		return "redirect:/ManageDino";
	}
	@PostMapping("/new-food")
	public String newFood(@ModelAttribute Food newFood) {
		foodService.create(newFood);
		return "redirect:/ManageDino";
	}
	@PostMapping("/new-safe")
	public String newSafe(@ModelAttribute ParkSafe newSafe) {
		parkService.create(newSafe);
		return "redirect:/ManageDino";
	}
}
