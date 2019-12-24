package com.springboot.fixedTermAccount.service;

import com.springboot.fixedTermAccount.document.FixedTermAccount;
import com.springboot.fixedTermAccount.dto.AccountDto;
import com.springboot.fixedTermAccount.dto.FixedTermAccountDto;
import com.springboot.fixedTermAccount.dto.PersonalDto;
import com.springboot.fixedTermAccount.dto.OperationDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FixedTermAccountInterface {
	

	public Flux<FixedTermAccount> findAll();
	public Mono<FixedTermAccount> findById(String id);
	public Mono<FixedTermAccount> save(FixedTermAccount fixedTermAccount);
	public Mono<FixedTermAccount> update(FixedTermAccount fixedTermAccount ,String id);
	public Mono<Void> delete(FixedTermAccount fixedTermAccount);

	
	public Mono<FixedTermAccount> findByNumAccount(String numAccount);
    public Mono<FixedTermAccount> saveOperation(OperationDto operationDto);
	public Mono<PersonalDto> saveHeadline(AccountDto accountDto);     
	public Mono<FixedTermAccountDto> saveHeadlines (FixedTermAccountDto fixedTermAccountDto);
	

}
