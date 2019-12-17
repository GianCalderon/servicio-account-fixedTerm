package com.springboot.fixedTermAccount.document;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "FixedTermAccount")
public class FixedTermAccount {
	
	@Id
	private String id;
	
	@NotNull(message = "Account' name must not be null")
	@NotEmpty(message = "name may not be empty")
	private String name;
	
	@NotNull(message = "Account' number must not be null")
	@NotEmpty(message = "number may not be empty")
	private String number;
	
	@NotNull(message = "Account' tea must not be null")
	private Double tea;
	
	@NotNull(message = "Account' state must not be null")
	@NotEmpty(message = "state may not be empty")
	private String state;
	
	@NotNull(message = "Account' balance must not be null")
	@Min(0)
	private Double balance;

}
