package com.example.springsecurityjwt.service;

import com.example.springsecurityjwt.recode.Health;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HealthService {
	List<Health> healths = List.of(
			new Health("Terry", "2024-04-14T10:00", "양호", ""),
			new Health("Sally", "2024-04-14T10:00", "양호", ""),
			new Health("Buster", "2024-04-14T10:00", "양호", ""),
			new Health("Lucy", "2024-04-14T10:00", "나쁨", "오른쪽 다리에 베인 상처 확인, 치료 완료."),
			new Health("Tom", "2024-04-14T11:00", "양호", ""),
			new Health("Lily", "2024-04-14T11:00", "양호", ""),
			new Health("Max", "2024-04-14T11:00", "양호", ""),
			new Health("Daisy", "2024-04-14T11:00", "양호", ""),
			new Health("Jack", "2024-04-14T13:00", "양호", ""),
			new Health("Luna", "2024-04-14T13:00", "양호", ""),
			new Health("Bruno", "2024-04-14T13:00", "나쁨", "최근 싸움으로 인해 발톱이 부러짐, 치료 완료."),
			new Health("Roxy", "2024-04-14T13:00", "양호", ""),
			new Health("Spike", "2024-04-14T14:00", "양호", ""),
			new Health("Rose", "2024-04-14T14:00", "양호", ""),
			new Health("Rocky", "2024-04-14T14:00", "양호", ""),
			new Health("Rex", "2024-04-14T14:00", "양호", ""),
			new Health("Alex", "2024-04-14T15:00", "나쁨", "나무에 걸려 날개를 다침, 치료 완료."),
			new Health("Sophie", "2024-04-14T15:00", "양호", ""),
			new Health("Victor", "2024-04-14T15:00", "양호", ""),
			new Health("Ava", "2024-04-14T15:00", "양호", "")
			);
	
	public List<Health> getHealths(){
		return healths;
	}
	
	public Health create(Health newHealth) {
		List<Health> extend = new ArrayList<>(healths);
		extend.add(newHealth);
		this.healths = List.copyOf(extend);
		return newHealth;
	}
}
