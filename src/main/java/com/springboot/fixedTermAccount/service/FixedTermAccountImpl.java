package com.springboot.fixedTermAccount.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.fixedTermAccount.client.PersonalClient;
import com.springboot.fixedTermAccount.document.FixedTermAccount;
import com.springboot.fixedTermAccount.dto.CuentaDto;
import com.springboot.fixedTermAccount.dto.FixedTermAccountDto;
import com.springboot.fixedTermAccount.dto.PersonalDto;
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
	PersonalClient webClientPer;
	
	
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

			s.setNumberAccount(fixedTermAccount.getNumberAccount());
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
		
		 LOGGER.info("Service -----> "+fixedTermAccountDto.toString());
          
		return save(convert.convertFixedTermAccount(fixedTermAccountDto)).flatMap(ca -> {

			fixedTermAccountDto.getHolders().forEach(p -> {

				p.setIdAccount(ca.getId());
				p.setNameAccount("Cuenta-Plazo-Fijo");

				webClientPer.save(p).block();

			});

			return Mono.just(fixedTermAccountDto);
		});
		
	}
	
//	@Override
//	public Mono<PersonalDto> saveAddCuenta(CuentaDto cuentaDto) {
//		
//		
//		 LOGGER.info("Service -----> "+cuentaDto.toString());
//		 
//	    return repo.save(convert.convertFixedTermAccountUpdate(cuentaDto)).flatMap(c->{
//	    	
//	    	return webClientPer.findById(cuentaDto.getDni()).flatMap(p->{
//	    		
//	    		LOGGER.info("Flujo Inicial  ---->: "+p.toString());
//	    		
//	    		List<Map<String,String>> lista=p.getIdCuentas();
//	            
//	    		 Map<String,String> listmap = new HashMap<String,String>();
//	    		 listmap.put(c.getId(),c.getName());
//	             lista.add(listmap);
//	           
//	             p.setIdCuentas(lista);
//	             
//	             LOGGER.info("Flujo Final ---->: "+p.toString());
//	             
//	            return webClientPer.update(p,cuentaDto.getDni());
//	            
//	 
//	    	});
//	    	
//	    });
//	}
	
	
	
	@Override
	public Mono<PersonalDto> saveAddCuenta(CuentaDto cuentaDto) {
		
	    return repo.save(convert.convertFixedTermAccountUpdate(cuentaDto)).flatMap(c->{
	    	
	    	return webClientPer.findByNumDoc(cuentaDto.getDni()).flatMap(titular->{
	    		
	    		LOGGER.info("Flujo Inicial ---->: "+titular.toString());
	            
	    		
	    		titular.setNameAccount(c.getNameAccount());
	    		titular.setIdAccount(c.getId());
	    		

	             LOGGER.info("Flujo Final ----->: "+titular.toString());
	             
	            return webClientPer.update(titular,cuentaDto.getDni());
	            
	 
	    	});
	    	
	    });
	}

}
