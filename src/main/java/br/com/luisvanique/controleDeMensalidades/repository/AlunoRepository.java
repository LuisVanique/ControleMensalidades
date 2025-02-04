package br.com.luisvanique.controleDeMensalidades.repository;

import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.luisvanique.controleDeMensalidades.model.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long>{

	boolean existsByTelefone(String telefone);

	Page<Aluno> findAll(Pageable pageable);

}
