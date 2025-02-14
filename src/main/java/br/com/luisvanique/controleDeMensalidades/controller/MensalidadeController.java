package br.com.luisvanique.controleDeMensalidades.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.luisvanique.controleDeMensalidades.dto.MensalidadeDTO;
import br.com.luisvanique.controleDeMensalidades.model.Mensalidade;
import br.com.luisvanique.controleDeMensalidades.service.MensalidadeService;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/mensalidade")
public class MensalidadeController {
	
	@Autowired
	private MensalidadeService mensalidadeService;

	@GetMapping
	public ResponseEntity<List<MensalidadeDTO>> findByFiltroStatus(@RequestParam(required = false) Integer status) {

		List<Mensalidade> mensalidades = mensalidadeService.findByFiltroStatus(status);
		List<MensalidadeDTO> mensalidadesDTO = mensalidades.stream().map(x -> new MensalidadeDTO(x)).toList();

		return ResponseEntity.ok().body(mensalidadesDTO);
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<MensalidadeDTO> aprovarPagamento(@PathVariable Long id) {
		mensalidadeService.aprovarPagamento(id);
		return ResponseEntity.ok().build();
	}
	
	
	
}
