package com.example.springsecurityjwt.service;

import com.example.springsecurityjwt.recode.ParkSafe;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParkSafeService {
	List<ParkSafe> parksafes = List.of(
			new ParkSafe("2024-01-10T18:00", "양호", "양호", ""),
			new ParkSafe("2024-02-10T18:00", "양호", "양호", "관람객들이 모사사우루스 우리에 음식물을 던지지 않도록 안내 필요."),
			new ParkSafe("2024-03-10T18:00", "양호", "양호", "")
			);
	
	public List<ParkSafe> getSafes(){
		return parksafes;
	}
	
	public ParkSafe create(ParkSafe newSafe) {
		List<ParkSafe> extend = new ArrayList<>(parksafes);
		extend.add(newSafe);
		this.parksafes = List.copyOf(extend);
		return newSafe;
	}
}
