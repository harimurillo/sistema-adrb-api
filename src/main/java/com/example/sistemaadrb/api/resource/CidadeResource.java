package com.example.sistemaadrb.api.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sistemaadrb.api.event.RecursoCriadoEvent;
import com.example.sistemaadrb.api.model.Cidade;
import com.example.sistemaadrb.api.repository.CidadeRepository;

@RestController
@RequestMapping("/cidades")
public class CidadeResource {
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<Cidade> listar() {
		return cidadeRepository.findAll();
	}
	
	@GetMapping("/{cod}")
	public ResponseEntity<Cidade> buscarCidadePeloCodigo(@PathVariable Long cod) {
		
		Optional<Cidade> cidade = this.cidadeRepository.findById(cod);
		
		return cidade.isPresent() ? ResponseEntity.ok(cidade.get()) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Cidade> criar(@Valid @RequestBody Cidade cidade, HttpServletResponse response) {
		
		Cidade cidadeSalva = cidadeRepository.save(cidade);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, cidadeSalva.getCod()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(cidadeSalva);
		
	}
	

}
