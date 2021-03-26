package br.com.zup.desafio.Proposta;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class PropostaControllerTest {

	@Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    
    
	@Test
	void teste01() throws Exception{
		
		String request = "{\n"
				+ "   \"documento\": \"388.924.460-29\",\n"
				+ "   \"email\": \"ae@gmail.com\",\n"
				+ "   \"nome\": \"Guilherme\",\n"
				+ "   \"endereco\": \"Rua Av. Paulista\",\n"
				+ "   \"salario\": 212\n"
				+ "}";
		
		//Proposta proposta = new Proposta("456.476.170-69","a@gmail.com", "Guilherme", "Rua Av. Paulista", new BigDecimal(100.00));
		
		 mockMvc.perform(MockMvcRequestBuilders.post("/proposta")
	                .accept(MediaType.APPLICATION_JSON)
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(request)
	        ).andExpect(MockMvcResultMatchers.status().is(201))
	                .andDo(MockMvcResultHandlers.print());
		
		assertNotNull(request);
	}


}
