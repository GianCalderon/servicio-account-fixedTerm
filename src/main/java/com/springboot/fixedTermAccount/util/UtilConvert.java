package com.springboot.fixedTermAccount.util;

import org.springframework.stereotype.Component;

import com.springboot.fixedTermAccount.document.FixedTermAccount;
import com.springboot.fixedTermAccount.dto.FixedTermAccountDto;

@Component
public class UtilConvert {
	
	
	public FixedTermAccount convertFixedTermAccount(FixedTermAccountDto fixedTermAccountDto) {

		FixedTermAccount fixedTermAccount = new FixedTermAccount();

		fixedTermAccount.setNumberAccount(fixedTermAccountDto.getNumberAccount());
		fixedTermAccount.setState(fixedTermAccountDto.getState());
		fixedTermAccount.setBalance(fixedTermAccountDto.getBalance());

		return fixedTermAccount;

	}

}
