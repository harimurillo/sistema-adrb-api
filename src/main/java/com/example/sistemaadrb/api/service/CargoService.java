package com.example.sistemaadrb.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.sistemaadrb.api.model.Cargo;
import com.example.sistemaadrb.api.repository.CargoRepository;

@Service
public class CargoService {
	
	@Autowired
	private CargoRepository cargoRepository;
	
	public Cargo atualizar(Long cod, Cargo cargo) {
		
		Cargo cargoSalvo = buscarCargoPeloCodigo(cod);
		
		BeanUtils.copyProperties(cargo, cargoSalvo, "cod");
		
		return this.cargoRepository.save(cargoSalvo);
	}

	private Cargo buscarCargoPeloCodigo(Long cod) {
		Cargo cargoSalvo = this.cargoRepository.findById(cod).orElseThrow(() -> new EmptyResultDataAccessException(1));
		return cargoSalvo;
	}

}
