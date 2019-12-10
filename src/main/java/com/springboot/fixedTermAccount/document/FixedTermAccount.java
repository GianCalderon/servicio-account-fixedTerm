package com.springboot.fixedTermAccount.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "FixedTermAccount")
public class FixedTermAccount {
	
	@Id
	private String id;
	private String numberAccount;
	private String state;
	private int balance;

}
