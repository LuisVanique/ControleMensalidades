package br.com.luisvanique.controleDeMensalidades.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.time.LocalDate;

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

import br.com.luisvanique.controleDeMensalidades.dto.AlunoDTO;
import br.com.luisvanique.controleDeMensalidades.model.Aluno;
import br.com.luisvanique.controleDeMensalidades.model.Endereco;
import br.com.luisvanique.controleDeMensalidades.repository.AlunoRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class AlunoControllerTest {

	private final String endpoint = "/aluno";

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private AlunoRepository repository;
	
	@Autowired
	private ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
	
	@AfterEach
	public void afterDeleteDB() {
		repository.deleteAll();
	}

	@Test
	@DisplayName("Deve registar um aluno com sucesso com dados corretos e retornar 201 CREATED")
	public void deveRegistrarAlunoComSucesso() throws Exception {
		Aluno aluno = instanciaAluno();
		AlunoDTO alunoDto = new AlunoDTO(aluno);
		String jsonString = objectMapper.writeValueAsString(alunoDto);

		RequestBuilder request = post(endpoint).contentType(MediaType.APPLICATION_JSON).content(jsonString);
		MockHttpServletResponse response = mvc.perform(request).andReturn().getResponse();

		assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
	}
	
	@Test
	@DisplayName("Nao pode registrar aluno com o mesmo telefone e retornar 400 Bad request")
	public void naoDeveRegistrarAlunoComMesmoTelefone() throws Exception {
		Aluno aluno = instanciaAluno();
		repository.save(aluno);
		AlunoDTO alunoDto = new AlunoDTO(aluno);
		String jsonString = objectMapper.writeValueAsString(alunoDto);
		
		RequestBuilder request = post(endpoint).contentType(MediaType.APPLICATION_JSON).content(jsonString);
		MockHttpServletResponse response = mvc.perform(request).andReturn().getResponse();

		
		assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());

	}
	
	@Test
	@DisplayName("Deve retornar 200 OK em requisicao get para todos os registros")
	public void deveTrazerTodosOsRegistros() throws Exception {
		RequestBuilder request = get(endpoint).contentType(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response = mvc.perform(request).andReturn().getResponse();
		
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
	}
	
	@Test
	@DisplayName("Deve retornar 200 OK em requisicao get para um registro de aluno")
	public void deveTrazerORegistroDeUmAluno() throws Exception {
		Aluno aluno = instanciaAluno();
		repository.save(aluno);
		RequestBuilder request = get(endpoint + "/" + aluno.getId()).contentType(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response = mvc.perform(request).andReturn().getResponse();
		
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
	}
	
	@Test
	@DisplayName("Deve inativar um aluno e Retornar 204 NO CONTENT")
	public void inativarUsuario() throws Exception {
		Aluno aluno = instanciaAluno();
		repository.saveAndFlush(aluno);
		
		RequestBuilder request = delete(endpoint + "/" + aluno.getId()).contentType(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response = mvc.perform(request).andReturn().getResponse();
		
		 Aluno alunoAtualizado = repository.findById(aluno.getId()).orElseThrow();
		
		assertThat(alunoAtualizado.getAtivo()).isEqualTo("N");
		assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
	}
	
	@Test
	@DisplayName("Deve Atualizar Aluno válido e retornar 200 OK")
	public void deveAtualizarAluno() throws Exception {
		Aluno aluno = instanciaAluno();
		repository.saveAndFlush(aluno);
		aluno.setNome("Teste!");
		AlunoDTO alunoDto = new AlunoDTO(aluno);
		String jsonString = objectMapper.writeValueAsString(alunoDto);
		
		RequestBuilder request = put(endpoint + "/" + aluno.getId()).contentType(MediaType.APPLICATION_JSON).
				content(jsonString);
		
		MockHttpServletResponse response = mvc.perform(request).andReturn().getResponse();
		
		Aluno alunoPosRequest = repository.findById(aluno.getId()).orElseThrow();
		
		assertThat(alunoPosRequest.getNome()).isEqualTo("Teste!");
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
	}
	
	@Test
	@DisplayName("Deve reativar um aluno e o ativo ficar como S")
	public void deveReativarAluno() throws Exception {
		Aluno aluno = instanciaAluno();
		repository.saveAndFlush(aluno);
		
		RequestBuilder request = put(endpoint + "/reativar/" + aluno.getId());
		
		MockHttpServletResponse response = mvc.perform(request).andReturn().getResponse();
		
		Aluno alunoPosRequest = repository.findById(aluno.getId()).orElseThrow();
		
		assertThat(alunoPosRequest.getAtivo()).isEqualTo("S");
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		
	}
	
	private Aluno instanciaAluno() {
		String nome = "Luis";
		String telefone = "11952925758";
		LocalDate dataNascimento = LocalDate.now();
		Endereco endereco = new Endereco("08150-320", "Rua Paulino", "Jd Robru");
		return new Aluno(nome, endereco, telefone, dataNascimento);
	}
}
