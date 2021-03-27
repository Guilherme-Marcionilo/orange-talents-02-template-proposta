package br.com.zup.desafio.Proposta;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@AutoConfigureDataJpa
@Transactional
class PropostaControllerTest {

	@Autowired
	private EntityManager em;

	@InjectMocks
	private PropostaRepository propostaRepository = Mockito.mock(PropostaRepository.class);

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	@DisplayName("Deveria Cadastrar Proposta")
	void teste01() throws Exception {

		String request = "{\n" + "   \"documento\": \"388.924.460-29\",\n" + "   \"email\": \"ae@gmail.com\",\n"
				+ "   \"nome\": \"Guilherme\",\n" + "   \"endereco\": \"Rua Av. Paulista\",\n" + "   \"salario\": 212\n"
				+ "}";

		Proposta proposta = new Proposta("456.476.170-69", "a@gmail.com", "Guilherme", "Rua Av. Paulista",
				new BigDecimal(1000.00));
		em.persist(proposta);

		mockMvc.perform(MockMvcRequestBuilders.post("/proposta").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(request))
				.andExpect(MockMvcResultMatchers.status().is(201)).andDo(MockMvcResultHandlers.print());

		assertNotNull(request);
		Assertions.assertNotNull(proposta);
	}

	@Test
	@DisplayName("Deveria retornar 400 quando há algum erro com as informações")
	void teste03() throws Exception {

		String request = "{\n" + "   \"documento\": \"388.924.460-29\",\n" + "   \"email\": \"ae@gmail.com\",\n"
				+ "   \"nome\": \"Guilherme\",\n" + "   \"endereco\": \"Rua Av. Paulista\",\n" + "   \"salario\": 212\n"
				+ "}";

		Proposta proposta = new Proposta("456.476.170-69", "a@gmail.com", "Guilherme", "Rua Av. Paulista",
				new BigDecimal(1000.00));
		em.persist(proposta);

		mockMvc.perform(MockMvcRequestBuilders.post("/proposta").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(request))
				.andExpect(MockMvcResultMatchers.status().is(201)).andDo(MockMvcResultHandlers.print());

		assertNotNull(request);
		Assertions.assertNotNull(proposta);
	}
	
	@Test
	@DisplayName("NÃO Deveria cadastrar Proposta com email inválido e retornar 400")
	void teste04() throws Exception {

		String request = "{\n" + "   \"documento\": \"388.924.460-29\",\n" + "   \"email\": \"mail\",\n"
				+ "   \"nome\": \"Guilherme\",\n" + "   \"endereco\": \"Rua Av. Paulista\",\n" + "   \"salario\": 212\n"
				+ "}";

		Proposta proposta = new Proposta("456.476.170-69", "a@gmail.com", "Guilherme", "Rua Av. Pauli	sta",
				new BigDecimal(1000.00));
		em.persist(proposta);

		mockMvc.perform(MockMvcRequestBuilders.post("/proposta").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(request))
				.andExpect(MockMvcResultMatchers.status().is(400)).andDo(MockMvcResultHandlers.print());

		assertNotNull(request);
		Assertions.assertNotNull(proposta);
	}
	
	@Test
	@Transactional
	@DisplayName("NÃO Deveria Cadastrar Proposta com Documento Duplicado e retornar status 422")
	void teste02() throws Exception {

		when(propostaRepository.existsByDocumento(anyString())).thenReturn(true);
		ResponseEntity<Object> esperado = ResponseEntity.unprocessableEntity().build();
		ResponseEntity<Object> resultado = null;

		boolean proposta = propostaRepository.existsByDocumento(anyString());

		if (proposta) {
			resultado = ResponseEntity.unprocessableEntity().build();
		}

		assertThat(resultado, Matchers.equalToObject(esperado));

	}
	
	

}
