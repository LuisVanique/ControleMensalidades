package br.com.luisvanique.controleDeMensalidades.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.com.luisvanique.controleDeMensalidades.dto.InstrutorDto;
import br.com.luisvanique.controleDeMensalidades.model.Instrutor;
import br.com.luisvanique.controleDeMensalidades.repository.InstrutorRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class InstrutorControllerTest {

	private final String endpoint = "/instrutor";

	@Autowired
	private MockMvc mvc;

	@Autowired
	private InstrutorRepository instrutorRepository;

	@Autowired
	private ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

	@AfterEach
	public void deleteDb() {
		instrutorRepository.deleteAll();
	}

	@Test
	@DisplayName("Deve cadastrar um instrutor corretamente e retonar 201 created")
	public void cadastrarInstrutorValido() throws Exception {
		Instrutor instrutor = instanciaInstrutor();
		InstrutorDto instrutorDto = new InstrutorDto(instrutor);
		String json = objectMapper.writeValueAsString(instrutorDto);
		
		RequestBuilder request = post(endpoint).contentType(MediaType.APPLICATION_JSON).content(json);
		MockHttpServletResponse response = mvc.perform(request).andReturn().getResponse();
		
		assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
	}
	
	@Test
	@DisplayName("Nao deve registrar Instrutor Com Mesmo Email e retornar 400 bad request")
	public void naoDeveCadastrarInstrutorComMesmoEmail() throws Exception{
		Instrutor instrutor = instanciaInstrutor();
		instrutorRepository.save(instrutor);
		
		InstrutorDto instrutorDto = new InstrutorDto(instrutor);
		String json = objectMapper.writeValueAsString(instrutorDto);
		
		RequestBuilder request = post(endpoint).contentType(MediaType.APPLICATION_JSON).content(json);
		MockHttpServletResponse response = mvc.perform(request).andReturn().getResponse();
		
		assertThat(response.getContentAsString()).contains("Email Ja cadastrado no sistema!");
		assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
	}
	
	@Test
	@DisplayName("Nao deve registrar Instrutor Com Mesmo Telefone e retornar 400 bad request")
	public void naoDeveCadastrarInstrutorComMesmoTelefone() throws Exception{
		Instrutor instrutor = instanciaInstrutor();
		instrutorRepository.save(instrutor);
		
		instrutor.setEmail("AlteradoEmail@gmail.com");
		InstrutorDto instrutorDto = new InstrutorDto(instrutor);

		String json = objectMapper.writeValueAsString(instrutorDto);
		
		RequestBuilder request = post(endpoint).contentType(MediaType.APPLICATION_JSON).content(json);
		MockHttpServletResponse response = mvc.perform(request).andReturn().getResponse();
		
		assertThat(response.getContentAsString()).contains("O Telefone já foi registrado no sistema!");
		assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
	}
	
	@Test
	@DisplayName("Deve Atualizar Instrutor válido e retornar 200 OK")
	public void deveAtualizarInstrutor() throws Exception {
		Instrutor instrutor = instanciaInstrutor();
		instrutorRepository.saveAndFlush(instrutor);
		instrutor.setNome("Teste!");
		InstrutorDto instrutorDto = new InstrutorDto(instrutor);
		String jsonString = objectMapper.writeValueAsString(instrutorDto);
		
		RequestBuilder request = put(endpoint + "/" + instrutor.getId()).contentType(MediaType.APPLICATION_JSON).
				content(jsonString);
		
		MockHttpServletResponse response = mvc.perform(request).andReturn().getResponse();
		
		Instrutor instrutorPosRequest = instrutorRepository.findById(instrutor.getId()).orElseThrow();
		
		assertThat(instrutorPosRequest.getNome()).isEqualTo("Teste!");
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
	}

	private Instrutor instanciaInstrutor() {
		return new Instrutor("Rogerio", "rogerio_academia", "cunhagustavo142@gmail.com", "584927123", "1199192300");
	}

}
