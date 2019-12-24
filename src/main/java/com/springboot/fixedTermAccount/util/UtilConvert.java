package com.springboot.fixedTermAccount.util;

import java.util.ArrayList;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.springboot.fixedTermAccount.document.FixedTermAccount;
import com.springboot.fixedTermAccount.dto.AccountDto;
import com.springboot.fixedTermAccount.dto.FixedTermAccountDto;

@Component
public class UtilConvert {
	
	 private static final Logger LOGGER = LoggerFactory.getLogger(UtilConvert.class);
	
	public FixedTermAccount convertFixedTermAccount(FixedTermAccountDto fixedTermAccountDto) {

		 LOGGER.info("Antes del Convertidor -----> "+fixedTermAccountDto.toString());
		 
		FixedTermAccount fixedTermAccount = new FixedTermAccount();

		fixedTermAccount.setNameAccount("Cuenta-Plazo-Fijo");
		fixedTermAccount.setNumberAccount("00104040"+String.valueOf((int)(Math.random()*99999999+1)));
		fixedTermAccount.setState(fixedTermAccountDto.getState());
		fixedTermAccount.setBalance(fixedTermAccountDto.getBalance());
		fixedTermAccount.setTea(fixedTermAccountDto.getTea());
		fixedTermAccount.setCreateDate(new Date());
		fixedTermAccount.setUpdateDate(new Date());
		fixedTermAccount.setIdOperation(new ArrayList<String>());
		
		 LOGGER.info("Antes del Convertidor -----> "+fixedTermAccountDto.toString());
		 
		return fixedTermAccount;

	}
	
	
	public FixedTermAccount convertAccountDto(AccountDto accountDto) {
		
		 LOGGER.info("Antes del Convertidor -----> "+accountDto.toString());

		FixedTermAccount  currentAccount = new FixedTermAccount();

		currentAccount.setNameAccount("Cuenta-Plazo-Fijo");
		currentAccount.setNumberAccount("00104040"+String.valueOf((int)(Math.random()*99999999+1)));
		currentAccount.setState(accountDto.getState());
		currentAccount.setBalance(accountDto.getBalance());
		currentAccount.setTea(accountDto.getTea());
		currentAccount.setCreateDate(new Date());
		currentAccount.setUpdateDate(new Date());
		currentAccount.setIdOperation(new ArrayList<String>());

		 LOGGER.info("Despues del Convertidor -----> "+accountDto.toString());
		
		return currentAccount;

	}

}
