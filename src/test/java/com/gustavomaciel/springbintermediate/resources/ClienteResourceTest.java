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

import net.minidev.json.JSONArray;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClienteResourceTest {
	
	@Autowired
	public WebApplicationContext context;
	
	private MockMvc mvc;
	
	@Before
	public void setup() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}

	@Test
	public void getCategoriaByIdWithSuccessOkStatus() throws Exception {
		String url = "/clientes/1";
		
		this.mvc.perform(get(url))
		.andExpect(status().isOk())
		.andExpect(jsonPath("id", is(1)))
		.andExpect(jsonPath("name", is("Maria Silva")))
		.andExpect(jsonPath("email", is("maria@gmail.com")))
		.andExpect(jsonPath("cpfOuCnpj", is("312321321")))
		.andExpect(jsonPath("tipo", is("PESSOAFISICA")))
		.andExpect(jsonPath("telefones", isA(JSONArray.class)))
		.andExpect(jsonPath("enderecos", isA(JSONArray.class)));
	}
	
	@Test
	public void returnExceptionResponseWhenIdDoesNotExistWithNotFoundStatus() throws Exception {
		String url = "/clientes/3";
		
		this.mvc.perform(get(url))
		.andExpect(status().isNotFound())
		.andExpect(jsonPath("status", is(404)))
		.andExpect(jsonPath("msg", containsString("Objeto não encontrado!")))
		.andExpect(jsonPath("timeStamp", isA(Long.class)));
	}

}
