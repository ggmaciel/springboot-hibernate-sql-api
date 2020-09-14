package com.gustavomaciel.springbintermediate.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import com.gustavomaciel.springbintermediate.domain.Cliente;
import com.gustavomaciel.springbintermediate.services.exceptions.ObjectNotFoundException;

@DataJpaTest
class ClienteServiceTest {
	
	@Autowired
	@SpyBean
	private ClienteService service;
	
	////
	@Test
	void findById() {
		Cliente obj = service.find(1);
		
		assertThat(obj).isInstanceOfAny(Cliente.class);
	}
	
	@Test
	void errorWhenIdDoesNotExist() {
		Throwable exception = assertThrows(ObjectNotFoundException.class, () -> service.find(3));
		
		 assertEquals("Objeto não encontrado! Id: 3, Tipo: com.gustavomaciel.springbintermediate.domain.Cliente", exception.getMessage());
	}

}
