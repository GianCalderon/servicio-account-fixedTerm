package com.springboot.fixedTermAccount.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.springboot.fixedTermAccount.document.FixedTermAccount;

public interface FixedTermAccountRepo extends ReactiveMongoRepository<FixedTermAccount, String> {

}
