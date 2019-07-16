package com.example.sistemaadrb.api.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.sistemaadrb.api.event.RecursoCriadoEvent;
import com.example.sistemaadrb.api.model.TipoTelefone;
import com.example.sistemaadrb.api.repository.TipoTelefoneRepository;
import com.example.sistemaadrb.api.service.TipoTelefoneService;

@RestController
@RequestMapping("/tipos-telefone")
public class TipoTelefoneResource {
	
	@Autowired
	private TipoTelefoneRepository tipoTelefoneRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private TipoTelefoneService tipoTelefoneService;
	
	@GetMapping
	public List<TipoTelefone> listar() {
		return tipoTelefoneRepository.findAll();
	}
	
	@GetMapping("/{cod}")
	public ResponseEntity<TipoTelefone> buscarTipoTelefonePeloCodigo(@PathVariable Long cod) {
		
		Optional<TipoTelefone> tipoTelefone = tipoTelefoneRepository.findById(cod);	
		
		return tipoTelefone.isPresent() ? ResponseEntity.ok(tipoTelefone.get()) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<TipoTelefone> criar(@Valid @RequestBody TipoTelefone tipoTelefone, HttpServletResponse response) {	
		
		TipoTelefone tipoTelefoneSalvo = tipoTelefoneRepository.save(tipoTelefone);	
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, tipoTelefoneSalvo.getCod()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(tipoTelefoneSalvo);
	}
	
	@DeleteMapping("/{cod}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long cod) {
		
		tipoTelefoneRepository.deleteById(cod);
	}
	
	@PutMapping("/{cod}")
	public ResponseEntity<TipoTelefone> atualizar(@PathVariable Long cod, @Valid @RequestBody TipoTelefone tipoTelefone) {
		
		TipoTelefone tipoTelefoneSalvo = tipoTelefoneService.atualizar(cod, tipoTelefone);
		
		return ResponseEntity.ok(tipoTelefoneSalvo);
	}
}
