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
import com.example.sistemaadrb.api.exceptionhandler.excecoes.ExcecaoQuandoNaoExisteCidade;
import com.example.sistemaadrb.api.model.Bairro;
import com.example.sistemaadrb.api.repository.BairroRepository;
import com.example.sistemaadrb.api.resource.dto.BairroDTO;
import com.example.sistemaadrb.api.resource.dto.CidadeDTO;
import com.example.sistemaadrb.api.service.BairroService;

@RestController
@RequestMapping("/bairros")
public class BairroResource {
	
	@Autowired
	private BairroRepository bairroRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private BairroService bairroService;
	
	@GetMapping
	public List<Bairro> listar() {
		return bairroRepository.findAll();
	}
	
	@GetMapping("/{cod}")
	public ResponseEntity<Bairro> buscarBairroPeloCodigo(@PathVariable Long cod) {
		
		Optional<Bairro> bairro = this.bairroRepository.findById(cod);
		
		return bairro.isPresent() ? ResponseEntity.ok(bairro.get()) : ResponseEntity.notFound().build(); 
	}
	
	@PostMapping
	public ResponseEntity<Bairro> criar(@Valid @RequestBody Bairro bairro, HttpServletResponse response) {
		
		if (null == bairro.getCidade() || null == bairro.getCidade().getCod()) {
			throw new ExcecaoQuandoNaoExisteCidade("Codigo da cidade é obrigatorio");
		}
		
		Bairro bairroSalvo = bairroRepository.save(bairro);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, bairroSalvo.getCod()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(bairroSalvo);
	}
	
	@DeleteMapping("/{cod}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long cod) {
		
		bairroRepository.deleteById(cod);
	}
	
	@PutMapping("/{cod}")
	public ResponseEntity<Bairro> atualizar(@PathVariable Long cod, @Valid @RequestBody BairroDTO nomeBairro) {
		
		Bairro bairroSalvo = bairroService.atualizar(cod, nomeBairro);
		
		return ResponseEntity.ok(bairroSalvo);
	}
	
	@PutMapping("/{cod}/cidade")
	@ResponseStatus(HttpStatus.OK)
	public void atualizarPropriedadeCidade(@PathVariable Long cod, @Valid @RequestBody CidadeDTO cidade) {
		bairroService.atualizarPropriedadeCidade(cod, cidade);
	}

}
