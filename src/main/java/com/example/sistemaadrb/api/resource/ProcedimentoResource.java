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
import com.example.sistemaadrb.api.model.Procedimento;
import com.example.sistemaadrb.api.repository.ProcedimentoRepository;
import com.example.sistemaadrb.api.service.ProcedimentoService;

@RestController
@RequestMapping("/procedimentos")
public class ProcedimentoResource {
	
	@Autowired
	private ProcedimentoRepository procedimentoRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private ProcedimentoService procedimentoService;
	
	@GetMapping
	public List<Procedimento> listar() {
		return procedimentoRepository.findAll();
	}
	
	@GetMapping("/{cod}")
	public ResponseEntity<Procedimento> buscarProcedimentoPeloCodigo(@PathVariable Long cod) {
		
		Optional<Procedimento> procedimento = procedimentoRepository.findById(cod);
		
		return procedimento.isPresent() ? ResponseEntity.ok(procedimento.get()) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Procedimento> criar(@Valid @RequestBody Procedimento procedimento, HttpServletResponse response) {
		
		Procedimento procedimentoSalvo = procedimentoRepository.save(procedimento);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, procedimentoSalvo.getCod()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(procedimentoSalvo);
	}
	
	@DeleteMapping("/{cod}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long cod) {
		
		procedimentoRepository.deleteById(cod);
	}
	
	@PutMapping("/{cod}")
	public ResponseEntity<Procedimento> atualizar(@PathVariable Long cod, @RequestBody Procedimento procedimento) {
		
		Procedimento procedimentoSalvo = procedimentoService.atualizar(cod, procedimento);
		
		return ResponseEntity.ok(procedimentoSalvo);
		
	}

}