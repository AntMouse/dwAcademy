package com.example.springsecurityjwt.service;

import com.example.springsecurityjwt.recode.Food;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FoodService {
	List<Food> foods = List.of(
			new Food("Terry", "2024-04-14T10:00", "고기", 800, ""),
			new Food("Sally", "2024-04-14T10:00", "고기", 750, ""),
			new Food("Buster", "2024-04-14T10:00", "고기", 150, ""),
			new Food("Lucy", "2024-04-14T10:00", "고기", 900, ""),
			new Food("Tom", "2024-04-14T11:00", "물고기", 50, ""),
			new Food("Lily", "2024-04-14T11:00", "물고기", 48, ""),
			new Food("Max", "2024-04-14T11:00", "물고기", 50, ""),
			new Food("Daisy", "2024-04-14T11:00", "물고기", 30, ""),
			new Food("Jack", "2024-04-14T13:00", "고기", 200, ""),
			new Food("Luna", "2024-04-14T13:00", "고기", 180, ""),
			new Food("Bruno", "2024-04-14T13:00", "고기", 220, ""),
			new Food("Roxy", "2024-04-14T13:00", "고기", 150, ""),
			new Food("Spike", "2024-04-14T14:00", "풀", 50, ""),
			new Food("Rose", "2024-04-14T14:00", "풀", 48, ""),
			new Food("Rocky", "2024-04-14T14:00", "풀", 30, ""),
			new Food("Rex", "2024-04-14T14:00", "풀", 70, ""),
			new Food("Alex", "2024-04-14T15:00", "고기", 2, ""),
			new Food("Sophie", "2024-04-14T15:00", "고기", 2, ""),
			new Food("Victor", "2024-04-14T15:00", "고기", 3, ""),
			new Food("Ava", "2024-04-14T15:00", "고기", 2, "")
			);
	
	public List<Food> getFoods(){
		return foods;
	}
	
	public Food create(Food newFood) {
		List<Food> extend = new ArrayList<>(foods);
		extend.add(newFood);
		this.foods = List.copyOf(extend);
		return newFood;
	}
}
