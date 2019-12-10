package com.springboot.fixedTermAccount.service;

import com.springboot.fixedTermAccount.document.FixedTermAccount;
import com.springboot.fixedTermAccount.dto.FixedTermAccountDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FixedTermAccountInterface {
	

	public Flux<FixedTermAccount> findAll();
	public Mono<FixedTermAccount> findById(String id);
	public Mono<FixedTermAccount> save(FixedTermAccount fixedTermAccount);
	public Mono<FixedTermAccount> update(FixedTermAccount fixedTermAccount ,String id);
	public Mono<Void> delete(FixedTermAccount fixedTermAccount);
	
	public Mono<FixedTermAccountDto> saveDto(FixedTermAccountDto fixedTermAccountDto);
	

}
