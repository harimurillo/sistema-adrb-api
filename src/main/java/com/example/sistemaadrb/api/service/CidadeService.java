package com.example.sistemaadrb.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.sistemaadrb.api.model.Cidade;
import com.example.sistemaadrb.api.repository.CidadeRepository;

@Service
public class CidadeService {
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	public Cidade atualizar(Long cod, Cidade cidade) {	
		
		Cidade cidadeSalva = cidadeRepository.findById(cod).orElseThrow(() -> new EmptyResultDataAccessException(1));	
		
		BeanUtils.copyProperties(cidade, cidadeSalva, "cod");
		
		return this.cidadeRepository.save(cidadeSalva);
		
	}
}
