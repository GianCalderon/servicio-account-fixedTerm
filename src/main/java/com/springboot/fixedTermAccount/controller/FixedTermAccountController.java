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

import com.springboot.fixedTermAccount.document.FixedTermAccount;
import com.springboot.fixedTermAccount.dto.FixedTermAccountDto;
import com.springboot.fixedTermAccount.service.FixedTermAccountImpl;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/fixedTermAccount")
public class FixedTermAccountController {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(FixedTermAccountController.class);
	
	
	@Autowired
	FixedTermAccountImpl service;

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
	public Mono<ResponseEntity<FixedTermAccount>> save(@RequestBody FixedTermAccount fixedTermAccount) {

		return service.save(fixedTermAccount)
				.map(f -> ResponseEntity.created(URI.create("/api/fixedTermAccount".concat(f.getId())))
						.contentType(MediaType.APPLICATION_JSON).body(f));

	}

	@PutMapping("/{id}")
	public Mono<ResponseEntity<FixedTermAccount>> update(@RequestBody FixedTermAccount fixedTermAccount,
			@PathVariable String id) {

		return service.update(fixedTermAccount, id)
				.map(f -> ResponseEntity.created(URI.create("/api/fixedTermAccount".concat(f.getId())))
						.contentType(MediaType.APPLICATION_JSON).body(f))
				.defaultIfEmpty(ResponseEntity.notFound().build());

	}

	@DeleteMapping("/{id}")
	public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {

		return service.findById(id).flatMap(f -> {
			return service.delete(f).then(Mono.just(new ResponseEntity<Void>(HttpStatus.ACCEPTED)));
		}).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));

	}

	@PostMapping("/saveDto")
	public Mono<ResponseEntity<FixedTermAccountDto>> saveDto(@RequestBody FixedTermAccountDto fixedTermAccountDto) {


		return service.saveDto(fixedTermAccountDto).map(s -> ResponseEntity.created(URI.create("/api/fixedTermAccount"))
				.contentType(MediaType.APPLICATION_JSON).body(s));

	}
	
	
	
	
	
	
	
}
