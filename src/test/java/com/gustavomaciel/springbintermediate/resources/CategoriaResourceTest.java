package com.gustavomaciel.springbintermediate.resources;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.isA;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoriaResourceTest {

	@Autowired
	public WebApplicationContext context;
	
	private MockMvc mvc;
	
	@Before
	public void setup() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}
	
	@Test
	public void getCategoriaByIdWithSuccessOkStatus() throws Exception {
		String url = "/categorias/1";
		
		this.mvc.perform(get(url))
		.andExpect(status().isOk())
		.andExpect(jsonPath("id", is(1)))
		.andExpect(jsonPath("name", is("Informática")))
		.andExpect(jsonPath("$.products[0].id", is(1)))
		.andExpect(jsonPath("$.products[0].price", is(2000.00)))
		.andExpect(jsonPath("$.products[0].name", is("Computador")));
	}
	
	@Test
	public void returnExceptionResponseWhenIdDoesNotExistWithNotFoundStatus() throws Exception {
		String url = "/categorias/3";
		
		this.mvc.perform(get(url))
		.andExpect(status().isNotFound())
		.andExpect(jsonPath("status", is(404)))
		.andExpect(jsonPath("msg", containsString("Objeto não encontrado!")))
		.andExpect(jsonPath("timeStamp", isA(Long.class)));
	}
}
