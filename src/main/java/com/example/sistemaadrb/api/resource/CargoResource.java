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
import com.example.sistemaadrb.api.model.Cargo;
import com.example.sistemaadrb.api.repository.CargoRepository;
import com.example.sistemaadrb.api.service.CargoService;

@RestController
@RequestMapping("/cargos")
public class CargoResource {
	
	@Autowired
	private CargoRepository cargoRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private CargoService cargoService;
	
	@GetMapping
	public List<Cargo> listar() {
		return cargoRepository.findAll();
	}
	
	@GetMapping("/{cod}")
	public ResponseEntity<Cargo> buscarCargoPeloCodigo(@PathVariable Long cod) {
		
		Optional<Cargo> cargo = this.cargoRepository.findById(cod);
		
		return cargo.isPresent() ? ResponseEntity.ok(cargo.get()) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Cargo> criar(@Valid @RequestBody Cargo cargo, HttpServletResponse response) {
		
		Cargo cargoSalvo = cargoRepository.save(cargo);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, cargoSalvo.getCod()));
				
		return ResponseEntity.status(HttpStatus.CREATED).body(cargoSalvo);
	}
	
	@DeleteMapping("/{cod}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long cod) {
		cargoRepository.deleteById(cod);
	}
	
	@PutMapping("/{cod}")
	public ResponseEntity<Cargo> atualizar(@PathVariable Long cod, @Valid @RequestBody Cargo cargo) {
		Cargo cargoSalvo = cargoService.atualizar(cod, cargo);
		return ResponseEntity.ok(cargoSalvo);
	}
	
	
}
