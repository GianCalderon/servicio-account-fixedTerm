package com.springboot.fixedTermAccount.dto;

import java.util.List;

import lombok.Data;

@Data
public class FixedTermAccountDto {

	private String numberAccount;
	private Double tea;
	private String state;
	private Double balance;
	private List<PersonalDto> headlines;
	
}
