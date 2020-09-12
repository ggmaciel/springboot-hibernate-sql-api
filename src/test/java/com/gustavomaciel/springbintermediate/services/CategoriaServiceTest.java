package com.gustavomaciel.springbintermediate.services;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import com.gustavomaciel.springbintermediate.domain.Categoria;

@DataJpaTest
public class CategoriaServiceTest {
	
	@Autowired
	@SpyBean
	private CategoriaService service;
	
	private Categoria informatica = new Categoria(1, "Informática");
	
	@Test
	void findById() {
		Integer id = 1;
		
		Categoria obj = service.find(id);
		
		assertThat(informatica).isEqualToComparingFieldByField(obj);
	}
}
