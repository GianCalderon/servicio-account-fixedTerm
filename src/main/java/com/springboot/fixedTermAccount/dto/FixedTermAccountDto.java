package com.springboot.fixedTermAccount.dto;

import java.util.List;

import lombok.Data;

@Data
public class FixedTermAccountDto {

	private String numberAccount;
	private String state;
	private int balance;
	private List<PersonalDto> holders;
	
}
