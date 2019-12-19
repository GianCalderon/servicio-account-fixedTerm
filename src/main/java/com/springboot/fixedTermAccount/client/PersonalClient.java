package com.springboot.fixedTermAccount.client;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.springboot.fixedTermAccount.dto.PersonalDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PersonalClient {
	
	
private static final Logger LOGGER = LoggerFactory.getLogger(PersonalClient.class);

    WebClient client = WebClient.create("http://localhost:8001/api/personal");	
	
//	@Autowired
//	private WebClient client;

	public Flux<PersonalDto> findAll() {
		
		return client.get().accept(MediaType.APPLICATION_JSON)
				.exchange()
				.flatMapMany(response ->response.bodyToFlux(PersonalDto.class));
	}


	public Mono<PersonalDto> findById(String id) {
		
		Map<String,Object> param=new HashMap<String,Object>();
		
		return client.get()
				.uri("/{id}",Collections.singletonMap("id",id))
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(PersonalDto.class);
		        
//		        .exchange()
//		        .flatMapMany(response ->response.bodyToMono(FamilyDTO.class));
	}

	
	public Mono<PersonalDto> save(PersonalDto personalDto) {
		
	 LOGGER.info("Listo a Enviar -----> "+personalDto.toString());
		
		return client.post()
			   .accept(MediaType.APPLICATION_JSON)
			   .contentType(MediaType.APPLICATION_JSON)
		       .body(BodyInserters.fromValue(personalDto))
			   .retrieve()
			   .bodyToMono(PersonalDto.class);
		
		
		
		
	}

	public Mono<Void> delete(String id) {
		
		return client.delete()
				.uri("/{id}",Collections.singletonMap("id",id))
				.exchange()
				.then();
	}

	public Mono<PersonalDto> update(PersonalDto personalDto, String id) {
		
		LOGGER.info("LISTO PARA ACTUALIZAR: "+personalDto.toString()+" ID --> :"+id);
		
		return client.put()
				   .uri("/{id}",Collections.singletonMap("id",id))
				   .accept(MediaType.APPLICATION_JSON)
				   .contentType(MediaType.APPLICATION_JSON)
				   .syncBody(personalDto)
				   .retrieve()
				   .bodyToMono(PersonalDto.class);
	}
	
  public Mono<PersonalDto> findByNumDoc(String id) {
		
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("id", id);
		return client.get().uri("/doc/{id}",param)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(PersonalDto.class);
		        
//		        .exchange()
//		        .flatMapMany(response ->response.bodyToMono(FamilyDTO.class));
	}

}
