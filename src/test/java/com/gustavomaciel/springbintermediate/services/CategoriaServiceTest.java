package com.gustavomaciel.springbintermediate.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import com.gustavomaciel.springbintermediate.domain.Categoria;
import com.gustavomaciel.springbintermediate.domain.Produto;
import com.gustavomaciel.springbintermediate.services.exceptions.ObjectNotFoundException;

@DataJpaTest
public class CategoriaServiceTest {
	
	@Autowired
	@SpyBean
	private CategoriaService service;
	
	private Categoria informatica = new Categoria(1, "Informática");
	Produto p1 = new Produto(1, "Computador", 2000.00);
	Produto p2 = new Produto(2, "Impressora", 800.00);
	Produto p3 = new Produto(3, "Mouse", 80.00);
	
	@Test
	void findById() {
		Integer id = 1;
		
		informatica.getProducts().addAll(Arrays.asList(p1, p2, p3));
		
		Categoria obj = service.find(id);
		
		assertThat(informatica).isEqualToComparingFieldByField(obj);
	}
	
	@Test
	void errorWhenIdDoesNotExist() {
		Throwable exception = assertThrows(ObjectNotFoundException.class, () -> service.find(3));
		
		 assertEquals("Objeto não encontrado! Id: 3, Tipo: com.gustavomaciel.springbintermediate.domain.Categoria", exception.getMessage());
	}
	
}
