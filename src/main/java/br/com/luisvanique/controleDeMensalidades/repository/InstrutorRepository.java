package br.com.luisvanique.controleDeMensalidades.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.luisvanique.controleDeMensalidades.model.Instrutor;

public interface InstrutorRepository extends JpaRepository<Instrutor, Long> {

	boolean existsByEmail(String email);

	boolean existsByTelefone(String telefone);

}
