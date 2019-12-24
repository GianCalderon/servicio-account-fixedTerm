package com.springboot.fixedTermAccount.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.springboot.fixedTermAccount.document.FixedTermAccount;

import reactor.core.publisher.Mono;

public interface FixedTermAccountRepo extends ReactiveMongoRepository<FixedTermAccount, String> {

	 public Mono<FixedTermAccount> findByNumberAccount(String numberAccount);
}
