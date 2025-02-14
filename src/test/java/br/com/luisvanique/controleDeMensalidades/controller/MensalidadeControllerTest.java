package br.com.luisvanique.controleDeMensalidades.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.time.LocalDate;

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

import br.com.luisvanique.controleDeMensalidades.model.Aluno;
import br.com.luisvanique.controleDeMensalidades.model.Endereco;
import br.com.luisvanique.controleDeMensalidades.model.Mensalidade;
import br.com.luisvanique.controleDeMensalidades.model.StatusMensalidade;
import br.com.luisvanique.controleDeMensalidades.repository.AlunoRepository;
import br.com.luisvanique.controleDeMensalidades.repository.MensalidadeRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class MensalidadeControllerTest {
	
	
	private final String endpoint = "/mensalidade";

	@Autowired
	private MockMvc mvc;

	@Autowired
	private MensalidadeRepository mensalidadeRepository;
	
	@Autowired
	private AlunoRepository alunoRepository;


	
	@Test
	@DisplayName("Aprovar mensalidade marcar seu status como 2 pago e resposta 200 OK")
	public void deveAprovarMensalidade() throws Exception {
		Aluno aluno = instanciaAluno();
		alunoRepository.save(aluno);
		Mensalidade mensalidade = new Mensalidade(aluno, LocalDate.now().plusMonths(1), 50.00, StatusMensalidade.PENDENTE.getStatus());
		mensalidadeRepository.save(mensalidade);
		
		RequestBuilder request = put(endpoint + "/" + mensalidade.getId()).contentType(MediaType.APPLICATION_JSON);
		MockHttpServletResponse response = mvc.perform(request).andReturn().getResponse();
		
		Mensalidade mensalidadePaga = mensalidadeRepository.findById(mensalidade.getId()).orElseThrow();
		
		assertThat(mensalidadePaga.getStatus()).isEqualTo(StatusMensalidade.PAGA.getStatus());
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
