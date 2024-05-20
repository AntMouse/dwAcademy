package com.example.springsecurityjwt.dto;

import lombok.Getter;
import lombok.Setter;


public class LoginRequestDto {
    private String memberName;
    private String password;
    

	public LoginRequestDto(String memberName, String password) {
		super();
		this.memberName = memberName;
		this.password = password;
	}
	public String getMemberName() {
		return memberName;
	}
	public String getPassword() {
		return password;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public void setPassword(String password) {
		this.password = password;
	}
    
    
    
}
