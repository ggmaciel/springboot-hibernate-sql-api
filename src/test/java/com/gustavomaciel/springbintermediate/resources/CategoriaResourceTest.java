package com.gustavomaciel.springbintermediate.resources;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.gustavomaciel.springbintermediate.domain.Categoria;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoriaResourceTest {

	@Autowired
	private TestRestTemplate restTemplate;
	
	private Categoria informatica = new Categoria(1, "Informática");
	
	@Test
	public void findCategoryById() {
		ResponseEntity<Categoria> response = restTemplate.getForEntity("/categorias/1", Categoria.class);
		
		assertThat(response.getStatusCodeValue()).isEqualTo(200);
		assertEquals(informatica, response.getBody());
		//assertThat(informatica).isEqualToComparingFieldByField(response.getBody());
	}
}
