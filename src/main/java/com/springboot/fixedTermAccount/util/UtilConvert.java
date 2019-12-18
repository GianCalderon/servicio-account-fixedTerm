package com.springboot.fixedTermAccount.util;

import java.util.ArrayList;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.springboot.fixedTermAccount.document.FixedTermAccount;
import com.springboot.fixedTermAccount.dto.CuentaDto;
import com.springboot.fixedTermAccount.dto.FixedTermAccountDto;
import com.springboot.fixedTermAccount.service.FixedTermAccountImpl;

@Component
public class UtilConvert {
	
	 private static final Logger LOGGER = LoggerFactory.getLogger(UtilConvert.class);
	
	public FixedTermAccount convertFixedTermAccount(FixedTermAccountDto fixedTermAccountDto) {

		 LOGGER.info("Antes del Convertidor -----> "+fixedTermAccountDto.toString());
		 
		FixedTermAccount fixedTermAccount = new FixedTermAccount();

		fixedTermAccount.setName("Cuenta-Plazo-Fijo");
		fixedTermAccount.setNumberAccount(fixedTermAccountDto.getNumberAccount());
		fixedTermAccount.setState(fixedTermAccountDto.getState());
		fixedTermAccount.setBalance(fixedTermAccountDto.getBalance());
		fixedTermAccount.setTea(fixedTermAccountDto.getTea());
		fixedTermAccount.setCreateDate(new Date());
		fixedTermAccount.setUpdateDate(new Date());
		fixedTermAccount.setIdOperation(new ArrayList<String>());
		
		 LOGGER.info("Antes del Convertidor -----> "+fixedTermAccountDto.toString());
		 
		return fixedTermAccount;

	}
	
	
	public FixedTermAccount convertFixedTermAccountUpdate(CuentaDto cuentaDto) {
		
		 LOGGER.info("Antes del Convertidor -----> "+cuentaDto.toString());

		FixedTermAccount  currentAccount = new FixedTermAccount();

		currentAccount.setName("Cuenta-Plazo-Fijo");
		currentAccount.setNumberAccount(cuentaDto.getNumberAccount());
		currentAccount.setState(cuentaDto.getState());
		currentAccount.setBalance(cuentaDto.getBalance());
		currentAccount.setTea(cuentaDto.getTea());
		currentAccount.setCreateDate(new Date());
		currentAccount.setUpdateDate(new Date());
		currentAccount.setIdOperation(new ArrayList<String>());

		 LOGGER.info("Despues del Convertidor -----> "+cuentaDto.toString());
		
		return currentAccount;

	}

}
