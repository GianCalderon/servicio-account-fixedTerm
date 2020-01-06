package com.springboot.fixedTermAccount.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.fixedTermAccount.client.PersonalClient;
import com.springboot.fixedTermAccount.document.FixedTermAccount;
import com.springboot.fixedTermAccount.dto.AccountDto;
import com.springboot.fixedTermAccount.repo.FixedTermAccountRepo;
import com.springboot.fixedTermAccount.util.UtilConvert;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FixedTermAccountImpl implements FixedTermAccountInterface {

	private static final Logger LOGGER = LoggerFactory.getLogger(FixedTermAccountImpl.class);

	@Autowired
	FixedTermAccountRepo repo;

	@Autowired
	UtilConvert convert;

	@Autowired
	PersonalClient client;

	@Override
	public Flux<FixedTermAccount> findAll() {
		return repo.findAll();
	}

	@Override
	public Mono<FixedTermAccount> findById(String id) {

		return repo.findById(id);
	}

	@Override
	public Mono<FixedTermAccount> save(AccountDto accountDto) {

		return client.findByNumDoc(accountDto.getNumDoc()).flatMap(persona ->{
		return repo.findByDniAndNameBank(accountDto.getNumDoc(), accountDto.getNameBank()).count().flatMap(AccountCant->{
			LOGGER.info("Cantidad cuentas por dni/banco: "+AccountCant);
			if(AccountCant==0) return repo.save(convert.convertAccountDto(accountDto));
			  else return Mono.empty();
          });
		});
  }

	@Override
	public Mono<FixedTermAccount> update(FixedTermAccount FixedTermAccount, String id) {

		return repo.findById(id).flatMap(s -> {

			s.setNameAccount(FixedTermAccount.getNameAccount());
			s.setNumberAccount(FixedTermAccount.getNumberAccount());
			s.setBalance(FixedTermAccount.getBalance());
			s.setState(FixedTermAccount.getState());
			s.setTea(FixedTermAccount.getTea());
			s.setHeadlines(FixedTermAccount.getHeadlines());
			s.setUpdateDate(new Date());

			return repo.save(s);
		});
	}

	@Override
	public Mono<Void> delete(FixedTermAccount FixedTermAccount) {

		return repo.delete(FixedTermAccount);
	}

	@Override
	public Mono<FixedTermAccount> findByNumAccount(String numberAccount) {

		return repo.findByNumberAccount(numberAccount);
	}

	public Flux<FixedTermAccount> findByDni(String dni) {

		return repo.findByDni(dni);
	}

}