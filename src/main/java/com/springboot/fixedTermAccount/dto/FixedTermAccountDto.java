package com.springboot.fixedTermAccount.dto;

import java.util.List;

import lombok.Data;

@Data
public class FixedTermAccountDto {

	private String number;
	private String tea;
	private String state;
	private int balance;
	private List<PersonalDto> holders;
	
}
