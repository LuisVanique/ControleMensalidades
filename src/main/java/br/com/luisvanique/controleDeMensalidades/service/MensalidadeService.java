package br.com.luisvanique.controleDeMensalidades.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.com.luisvanique.controleDeMensalidades.model.Aluno;
import br.com.luisvanique.controleDeMensalidades.model.ConstanteAtivos;
import br.com.luisvanique.controleDeMensalidades.model.Mensalidade;
import br.com.luisvanique.controleDeMensalidades.model.StatusMensalidade;
import br.com.luisvanique.controleDeMensalidades.repository.AlunoRepository;
import br.com.luisvanique.controleDeMensalidades.repository.MensalidadeRepository;
import jakarta.transaction.Transactional;

@Service
public class MensalidadeService {

	@Autowired
	private MensalidadeRepository mensalidadeRepository;

	@Autowired
	private AlunoRepository alunosRepository;

	public List<Mensalidade> findByFiltroStatus(Integer status) {
		if (status != null) {
			return mensalidadeRepository.findByFiltrosStatus(status);
		}
		return mensalidadeRepository.findAll();
	}

	public void aprovarPagamento(Long id) {
		Mensalidade mensalidade = mensalidadeRepository.findById(id).orElseThrow();
		mensalidade.setStatus(StatusMensalidade.PAGA.getStatus());
		mensalidadeRepository.save(mensalidade);
	}

	@Scheduled(cron = "0 0 0 20 * ?")
	public void gerarMensalidades() {
		List<Aluno> alunos = alunosRepository.findAll();
		LocalDate hoje = LocalDate.now();
		for (Aluno aluno : alunos) {
			if (alunoInativo(aluno))
				continue;

			if (!possuiMensalidadeParaOMesSeguinte(aluno, hoje))
				dadosMensalidade(aluno, hoje); // Só cria a mensalidade se não existir para o mês seguinte

		}
		atualizarMensalidadesVencidas();
		inativarAlunosComMensalidadesVencidas();
	}

	@Transactional
	private void dadosMensalidade(Aluno aluno, LocalDate referencia) {
		LocalDate dataVencimento = referencia.plusMonths(1).withDayOfMonth(20);

		Mensalidade mensalidade = new Mensalidade();
		mensalidade.setAluno(aluno);
		mensalidade.setDataVencimento(dataVencimento);
		mensalidade.setValor(50.00);
		mensalidade.setStatus(StatusMensalidade.PENDENTE.getStatus());

		mensalidadeRepository.save(mensalidade);
	}

	private boolean alunoInativo(Aluno aluno) {
		if (aluno.getAtivo().equals(ConstanteAtivos.INATIVO.getValor())) {
			return true;
		}
		return false;
	}

	@Transactional
	private void atualizarMensalidadesVencidas() {
		List<Mensalidade> mensalidades = mensalidadeRepository.findAll();
		LocalDate hoje = LocalDate.now();

		for (Mensalidade mensalidade : mensalidades) {
			if (mensalidade.getDataVencimento().isBefore(hoje)
					&& mensalidade.getStatus() == StatusMensalidade.PENDENTE.getStatus()) {
				mensalidade.setStatus(StatusMensalidade.VENCIDA.getStatus());
				mensalidadeRepository.save(mensalidade);
			}
		}
	}

	@Transactional
	private void inativarAlunosComMensalidadesVencidas() {
		List<Aluno> alunos = alunosRepository.findAll();

		for (Aluno aluno : alunos) {
			if (contaMensalidadesVencidas(aluno) >= 3) {
				aluno.setAtivo(ConstanteAtivos.INATIVO.getValor());
				alunosRepository.save(aluno);
			}
		}
	}

	private Integer contaMensalidadesVencidas(Aluno aluno) {
		List<Mensalidade> mensalidades = mensalidadeRepository.findByAluno(aluno);
		int count = 0;

		for (Mensalidade mensalidade : mensalidades) {
			if (mensalidade.getStatus() == StatusMensalidade.VENCIDA.getStatus()) {
				count++;
			}
		}
		return count;
	}

	private boolean possuiMensalidadeParaOMesSeguinte(Aluno aluno, LocalDate hoje) {
		LocalDate dataVencimento = hoje.plusMonths(1).withDayOfMonth(20);
		return mensalidadeRepository.existsByAlunoAndDataVencimento(aluno, dataVencimento);
	}
}
