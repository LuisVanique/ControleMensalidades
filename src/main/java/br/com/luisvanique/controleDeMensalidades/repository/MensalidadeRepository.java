package br.com.luisvanique.controleDeMensalidades.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.luisvanique.controleDeMensalidades.model.Aluno;
import br.com.luisvanique.controleDeMensalidades.model.Mensalidade;

public interface MensalidadeRepository extends JpaRepository<Mensalidade, Long>{

	@Query("select u from Mensalidade u where :status = u.status")
	List<Mensalidade> findByFiltrosStatus(@Param("status") Integer status);

	List<Mensalidade> findByAluno(Aluno aluno);

	boolean existsByAlunoAndDataVencimento(Aluno aluno, LocalDate dataVencimento);

}
