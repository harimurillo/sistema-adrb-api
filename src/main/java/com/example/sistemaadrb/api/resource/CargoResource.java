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

import com.example.sistemaadrb.api.model.Cargo;
import com.example.sistemaadrb.api.repository.CargoRepository;

@RestController
@RequestMapping("/cargos")
public class CargoResource {
	
	@Autowired
	private CargoRepository cargoRepository;
	
	@GetMapping
	public List<Cargo> listar() {
		return cargoRepository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Cargo> criar(@Valid @RequestBody Cargo cargo, HttpServletResponse response) {
		Cargo cargoSalvo = cargoRepository.save(cargo);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{cod}").buildAndExpand(cargoSalvo.getCod()).toUri();
		
		return ResponseEntity.created(uri).body(cargoSalvo);
	}
	
	@GetMapping("/{cod}")
	public ResponseEntity<Cargo> buscarCargoPeloCodigo(@PathVariable Long cod) {
		Optional<Cargo> cargo = this.cargoRepository.findById(cod);
		return cargo.isPresent() ? ResponseEntity.ok(cargo.get()) : ResponseEntity.notFound().build();
	}

}
