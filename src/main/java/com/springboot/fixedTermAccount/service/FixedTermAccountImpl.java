package com.springboot.fixedTermAccount.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.fixedTermAccount.client.PersonalClient;
import com.springboot.fixedTermAccount.document.FixedTermAccount;
import com.springboot.fixedTermAccount.dto.AccountClient;
import com.springboot.fixedTermAccount.dto.AccountDto;
import com.springboot.fixedTermAccount.dto.FixedTermAccountDto;
import com.springboot.fixedTermAccount.dto.OperationDto;
import com.springboot.fixedTermAccount.dto.PersonalDto;
import com.springboot.fixedTermAccount.repo.FixedTermAccountRepo;
import com.springboot.fixedTermAccount.util.CodAccount;
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
	PersonalClient client;
	
	
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

			s.setNameAccount(fixedTermAccount.getNameAccount());
			s.setNumberAccount(fixedTermAccount.getNumberAccount());
			s.setBalance(fixedTermAccount.getBalance());
			s.setState(fixedTermAccount.getState());
			s.setTea(fixedTermAccount.getTea());
			s.setUpdateDate(fixedTermAccount.getUpdateDate());
			s.setCreateDate(fixedTermAccount.getCreateDate());
			s.setIdOperation(fixedTermAccount.getIdOperation());
			
			return repo.save(s);
			});
	}

	@Override
	public Mono<Void> delete(FixedTermAccount fixedTermAccount) {
		return repo.delete(fixedTermAccount);
	}
	

	@Override
	public Mono<FixedTermAccount> findByNumAccount(String numAccount) {
		return repo.findByNumberAccount(numAccount);
	}


	@Override
	public Mono<PersonalDto> saveHeadline(AccountDto accountDto) {
		
		return client.extractAccounts(accountDto.getNumDoc()).collectList().flatMap(cuentas -> {
			
			int cont = 0;

		     for (int i = 0; i < cuentas.size(); i++) {

					AccountClient obj = cuentas.get(i);

					LOGGER.info("PRUEBA 3 --->" + accountDto.toString());

				    if (obj.getNumberAccount().substring(0, 6).equals(CodAccount.COD_CURRENT_ACCOUNT)) cont++;

				}
		     
				if (cont == 0) {

					return repo.save(convert.convertAccountDto(accountDto)).flatMap(cuenta -> {

						return client.findByNumDoc(accountDto.getNumDoc()).flatMap(titular -> {

							LOGGER.info("Flujo Inicial ---->: " + titular.toString());

							titular.setIdAccount(cuenta.getId());
							titular.setNameAccount(cuenta.getNameAccount());
							titular.setNumberAccount(cuenta.getNumberAccount());

							LOGGER.info("Flujo Final ----->: " + titular.toString());

							return client.update(titular, accountDto.getNumDoc()).flatMap(p->{
								
								p.setIdAccount(cuenta.getId());
								return Mono.just(p);
							});

						});

					});

				} else {

					return Mono.empty();
				}

			});
	}

	@Override
	public Mono<FixedTermAccountDto> saveHeadlines(FixedTermAccountDto fixedTermAccountDto) {
		return save(convert.convertFixedTermAccount(fixedTermAccountDto)).flatMap(cuenta -> {

			fixedTermAccountDto.getHeadlines().forEach(titular -> {

				titular.setIdAccount(cuenta.getId());
				titular.setNameAccount(cuenta.getNameAccount());
				titular.setNumberAccount(cuenta.getNumberAccount());

				client.save(titular);

			});

			return Mono.just(fixedTermAccountDto);
		});
	}
	
	@Override
	public Mono<FixedTermAccount> saveOperation(OperationDto operationDto) {
		
		return repo.findByNumberAccount(operationDto.getNumAccount()).flatMap(p->{

			if(operationDto.getTipoMovement().equals("debito")) {
				
				p.setBalance(p.getBalance()-operationDto.getAmount());
				return repo.save(p);
				
			}else if(operationDto.getTipoMovement().equals("abono")) {
				
				p.setBalance(p.getBalance()+operationDto.getAmount());
				return repo.save(p);
			}
			
			return repo.save(p);

		});
	}



	
	

	

	
}
