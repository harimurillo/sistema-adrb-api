package com.example.sistemaadrb.api.listener;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.sistemaadrb.api.event.RecursoCriadoEvent;

@Component
public class RecursoCriadoListener implements ApplicationListener<RecursoCriadoEvent>{

	@Override
	public void onApplicationEvent(RecursoCriadoEvent recursoCriadoEvent) {
		
		HttpServletResponse response = recursoCriadoEvent.getResponse();
		Long cod = recursoCriadoEvent.getCod();
		
		adicionarHeaderLocation(response, cod);

	}

	private void adicionarHeaderLocation(HttpServletResponse response, Long cod) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{cod}").buildAndExpand(cod).toUri();
		response.setHeader("Location", uri.toASCIIString());
	}

}
