package com.example.sistemaadrb.api.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.sistemaadrb.api.model.Cidade;
import com.example.sistemaadrb.api.repository.CidadeRepository;

@RestController
@RequestMapping("/cidades")
public class CidadeResource {
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
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
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{cod}").buildAndExpand(cidadeSalva.getCod()).toUri();
		
		return ResponseEntity.created(uri).body(cidadeSalva);
		
	}
	

}
