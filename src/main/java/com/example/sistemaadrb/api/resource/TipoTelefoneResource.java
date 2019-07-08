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

import com.example.sistemaadrb.api.model.TipoTelefone;
import com.example.sistemaadrb.api.repository.TipoTelefoneRepository;

@RestController
@RequestMapping("/tipos-telefone")
public class TipoTelefoneResource {
	
	@Autowired
	private TipoTelefoneRepository tipoTelefoneRepository;
	
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
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{cod}").buildAndExpand(tipoTelefoneSalvo.getCod()).toUri();
		
		return ResponseEntity.created(uri).body(tipoTelefoneSalvo);
	}
}
