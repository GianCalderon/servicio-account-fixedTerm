package com.springboot.fixedTermAccount.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.springboot.fixedTermAccount.document.FixedTermAccount;
import com.springboot.fixedTermAccount.document.Headline;
import com.springboot.fixedTermAccount.dto.AccountDto;

@Component
public class UtilConvert {
	
	 private static final Logger LOGGER = LoggerFactory.getLogger(UtilConvert.class);
	
	
		public FixedTermAccount convertAccountDto(AccountDto accountDto) {
			
			 LOGGER.info("convetir 1 -->"+accountDto.toString());
			 
			 List<Headline> listHeadline=new ArrayList<Headline>();
			 Headline headline=new Headline();
			 headline.setNumDoc(accountDto.getNumDoc());
			 listHeadline.add(headline);

			FixedTermAccount  FixedTermAccount = new FixedTermAccount();

			FixedTermAccount.setNameBank(accountDto.getNameBank());
			FixedTermAccount.setHeadlines(listHeadline);
			FixedTermAccount.setNameAccount(CodAccount.NAME_FIXEDTERM_ACCOUNT);
			FixedTermAccount.setNumberAccount(CodAccount.COD_FIXEDTERM_ACCOUNT+String.valueOf((int)(Math.random()*99999999+1)));
			FixedTermAccount.setState("Activo");
			FixedTermAccount.setBalance(accountDto.getBalance());
			FixedTermAccount.setTea(12.5);
			FixedTermAccount.setCreateDate(new Date());
			FixedTermAccount.setUpdateDate(new Date());
			

			 LOGGER.info("convetir 2-->"+FixedTermAccount.toString());
			return FixedTermAccount;

		}

}
