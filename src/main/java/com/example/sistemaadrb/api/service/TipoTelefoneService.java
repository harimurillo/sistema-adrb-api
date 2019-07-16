package com.example.sistemaadrb.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.sistemaadrb.api.model.TipoTelefone;
import com.example.sistemaadrb.api.repository.TipoTelefoneRepository;

@Service
public class TipoTelefoneService {
	
	@Autowired
	private TipoTelefoneRepository tipoTelefoneRepository;
	
	public TipoTelefone atualizar(Long cod, TipoTelefone tipoTelefone) {
		
		TipoTelefone tipoTelefoneSalvo = this.tipoTelefoneRepository.findById(cod).orElseThrow(() -> new EmptyResultDataAccessException(1));
		
		BeanUtils.copyProperties(tipoTelefone, tipoTelefoneSalvo, "cod");	
		
		return this.tipoTelefoneRepository.save(tipoTelefoneSalvo);
	}

}
