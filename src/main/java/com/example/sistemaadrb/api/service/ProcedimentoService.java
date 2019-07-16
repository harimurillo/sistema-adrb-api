package com.example.sistemaadrb.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.sistemaadrb.api.model.Procedimento;
import com.example.sistemaadrb.api.repository.ProcedimentoRepository;

@Service
public class ProcedimentoService {
	
	@Autowired
	private ProcedimentoRepository procedimentoRepository;
	
	public Procedimento atualizar(Long cod, Procedimento procedimento) {
		
		Procedimento procedimentoSalvo = procedimentoRepository.findById(cod).orElseThrow(() -> new EmptyResultDataAccessException(1));
		
		BeanUtils.copyProperties(procedimento, procedimentoSalvo, "cod");
		
		return this.procedimentoRepository.save(procedimentoSalvo);
		
	}

}
