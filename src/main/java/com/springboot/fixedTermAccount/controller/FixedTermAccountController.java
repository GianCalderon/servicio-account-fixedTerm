package com.springboot.fixedTermAccount.controller;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.fixedTermAccount.client.PersonalClient;
import com.springboot.fixedTermAccount.document.FixedTermAccount;
import com.springboot.fixedTermAccount.dto.AccountDto;
import com.springboot.fixedTermAccount.service.FixedTermAccountImpl;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/fixedTermAccount")
public class FixedTermAccountController {

	private static final Logger LOGGER = LoggerFactory.getLogger(FixedTermAccountController.class);

	@Autowired
	FixedTermAccountImpl service;
	
	@Autowired
	PersonalClient client;

	@GetMapping
	public Mono<ResponseEntity<Flux<FixedTermAccount>>> toList() {

		return Mono.just(ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(service.findAll()));

	}

	@GetMapping("/{id}")
	public Mono<ResponseEntity<FixedTermAccount>> search(@PathVariable String id) {
		
		return service.findById(id).map(s -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(s))
				.defaultIfEmpty(ResponseEntity.notFound().build());

	}
	
	@PostMapping
	public Mono<ResponseEntity<FixedTermAccount>> save(@RequestBody AccountDto accountDto) {
		
		LOGGER.info("accountDto ---> "+accountDto.toString());

		return service.save(accountDto)
				.map(s -> ResponseEntity.created(URI.create("/api/fixedTermAccount".concat(s.getId())))
						 .contentType(MediaType.APPLICATION_JSON).body(s))
				         .defaultIfEmpty(new ResponseEntity<FixedTermAccount>(HttpStatus.NOT_FOUND));

	}

	@PutMapping("/{id}")
	public Mono<ResponseEntity<FixedTermAccount>> update(@RequestBody FixedTermAccount FixedTermAccount,
			@PathVariable String id) {
		
		
		LOGGER.info("Controller ----> "+FixedTermAccount.toString());

		return service.update(FixedTermAccount, id)
				.map(s -> ResponseEntity.created(URI.create("/api/fixedTermAccount".concat(s.getId())))
						.contentType(MediaType.APPLICATION_JSON).body(s))
				.defaultIfEmpty(ResponseEntity.notFound().build());

	}
	

	@DeleteMapping("/{id}")
	public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {

		return service.findById(id).flatMap(s -> {
			return service.delete(s).then(Mono.just(new ResponseEntity<Void>(HttpStatus.ACCEPTED)));
		}).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));

	}
	
	//OPERACIONES QUE CONSUMEN SERVICIO

	
	@GetMapping("/dni/{dni}")
	public Flux<FixedTermAccount> searchByDni(@PathVariable String dni) {
				
		return service.findByDni(dni);

	}
	
	
	
	@GetMapping("/account/{numberAccount}")
	public Mono<ResponseEntity<FixedTermAccount>> searchByNumAccount(@PathVariable String numberAccount) {
		
		LOGGER.info("NUMERO DE CUENTA :--->"+numberAccount);

		return service.findByNumAccount(numberAccount).map(s -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(s))
				.defaultIfEmpty(ResponseEntity.notFound().build());

	}

	
	
	

}
