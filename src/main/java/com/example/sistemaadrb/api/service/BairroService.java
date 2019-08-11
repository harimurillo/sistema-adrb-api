package com.example.sistemaadrb.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.sistemaadrb.api.exceptionhandler.excecoes.ExcecaoQuandoNaoExisteCidade;
import com.example.sistemaadrb.api.model.Bairro;
import com.example.sistemaadrb.api.model.Cidade;
import com.example.sistemaadrb.api.repository.BairroRepository;
import com.example.sistemaadrb.api.repository.CidadeRepository;
import com.example.sistemaadrb.api.resource.dto.BairroDTO;
import com.example.sistemaadrb.api.resource.dto.CidadeDTO;

@Service
public class BairroService {
	
	@Autowired
	private BairroRepository bairroRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	public Bairro atualizar(Long cod, BairroDTO atributos) {
		
		Bairro bairroProcurado = buscarBairroPeloCodigo(cod);
		
		Optional<Cidade> codCidadeProcurada = cidadeRepository.findById(atributos.getCodCidade());
		
		if (codCidadeProcurada == null) {
			throw new ExcecaoQuandoNaoExisteCidade("Codigo da cidade é obrigatorio");
		}

		bairroProcurado.setNomeBairro(atributos.getNomeBairro());
		
		bairroProcurado.setCidade(codCidadeProcurada.get());
				
		return this.bairroRepository.save(bairroProcurado);
	}
	
	public void atualizarPropriedadeCidade(Long cod, CidadeDTO atributos) {
		
		Bairro bairroProcurado = buscarBairroPeloCodigo(cod);
		
		Optional<Cidade> cidadeProcurada = cidadeRepository.findById(atributos.getCod());
		
		bairroProcurado.setCidade(cidadeProcurada.get());
		
		bairroRepository.save(bairroProcurado);
	}
	
	private Bairro buscarBairroPeloCodigo(Long cod) {
		
		Bairro bairro = this.bairroRepository.findById(cod).orElseThrow(() -> new EmptyResultDataAccessException(1));
		
		return bairro;
	}
	
}
