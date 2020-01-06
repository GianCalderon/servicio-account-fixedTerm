package com.springboot.fixedTermAccount.service;

import com.springboot.fixedTermAccount.document.FixedTermAccount;
import com.springboot.fixedTermAccount.dto.AccountDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FixedTermAccountInterface{

	public Flux<FixedTermAccount> findAll();
	public Mono<FixedTermAccount> findById(String id);
	public Mono<FixedTermAccount> save(AccountDto accountDto);
	public Mono<FixedTermAccount> update(FixedTermAccount FixedTermAccount, String id);
	public Mono<Void> delete(FixedTermAccount FixedTermAccount);

	public Mono<FixedTermAccount> findByNumAccount(String id);
	public Flux<FixedTermAccount> findByDni(String id);
	
    

}
