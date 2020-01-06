package com.springboot.fixedTermAccount.repo;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.springboot.fixedTermAccount.document.FixedTermAccount;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FixedTermAccountRepo extends ReactiveMongoRepository<FixedTermAccount, String> {

	 public Mono<FixedTermAccount> findByNumberAccount(String numberAccount);
	 
	 @Query("{'headlines.dni': ?0 }") 
	 public Flux<FixedTermAccount> findByDni(String dni);
		
	 @Query("{'headlines.dni': ?0 , 'nameBank': ?1}") 
	 public Flux<FixedTermAccount> findByDniAndNameBank(String dni , String nameBank);
		
}
