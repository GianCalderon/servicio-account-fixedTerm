package com.springboot.fixedTermAccount.util;

import org.springframework.stereotype.Component;

import com.springboot.fixedTermAccount.document.FixedTermAccount;
import com.springboot.fixedTermAccount.dto.FixedTermAccountDto;

@Component
public class UtilConvert {
	
	
	public FixedTermAccount convertFixedTermAccount(FixedTermAccountDto fixedTermAccountDto) {

		FixedTermAccount fixedTermAccount = new FixedTermAccount();

		fixedTermAccount.setNumber(fixedTermAccountDto.getNumber());
		fixedTermAccount.setState(fixedTermAccountDto.getState());
		fixedTermAccount.setBalance(fixedTermAccountDto.getBalance());
		fixedTermAccount.setName("Cuenta Plazo Fijo");
		return fixedTermAccount;

	}

}
