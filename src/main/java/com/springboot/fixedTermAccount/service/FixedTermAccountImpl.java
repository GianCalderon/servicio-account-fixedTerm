package com.springboot.fixedTermAccount.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.fixedTermAccount.client.PersonalClient;
import com.springboot.fixedTermAccount.document.FixedTermAccount;
import com.springboot.fixedTermAccount.dto.FixedTermAccountDto;
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
	PersonalClient webClient;
	
	
	@Override
	public Flux<FixedTermAccount> findAll() {
		
		return repo.findAll();
	}

	@Override
	public Mono<FixedTermAccount> findById(String id) {
	
		return repo.findById(id);
	}

	@Override
	public Mono<FixedTermAccount> save(FixedTermAccount fixedTermAccount) {
		// TODO Auto-generated method stub
		return repo.save(fixedTermAccount);
	}

	@Override
	public Mono<FixedTermAccount> update(FixedTermAccount fixedTermAccount, String id) {
		
		return repo.findById(id).flatMap(s -> {

			s.setNumber(fixedTermAccount.getNumber());
			s.setBalance(fixedTermAccount.getBalance());
			s.setState(fixedTermAccount.getState());
			return repo.save(s);
			});
	}

	@Override
	public Mono<Void> delete(FixedTermAccount fixedTermAccount) {
		// TODO Auto-generated method stub
		return repo.delete(fixedTermAccount);
	}

	@Override
	public Mono<FixedTermAccountDto> saveDto(FixedTermAccountDto fixedTermAccountDto) {
		
          LOGGER.info("Service: "+fixedTermAccountDto.toString());
          
		return save(convert.convertFixedTermAccount(fixedTermAccountDto)).flatMap(ca -> {

			fixedTermAccountDto.getHolders().forEach(p -> {

				p.setIdCuenta(ca.getId());

				webClient.save(p).block();

			});

			return Mono.just(fixedTermAccountDto);
		});
		
	}

}
