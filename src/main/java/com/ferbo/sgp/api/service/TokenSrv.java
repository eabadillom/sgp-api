package com.ferbo.sgp.api.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ferbo.sgp.api.model.Empleado;
import com.ferbo.sgp.api.model.Token;
import com.ferbo.sgp.api.repository.TokenRepo;
import com.ferbo.sgp.api.tool.SecurityTool;

@Service
public class TokenSrv {
	
	@Autowired
	private TokenRepo tokenRepository;
	
	@Autowired
	private SecurityTool securityTool;
	
	public Token generaToken(Empleado empleado) {
		Token token = null;
		LocalDateTime caducidad = null;
		
		LocalDateTime inicio = LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0, 0, 0));
		LocalDateTime fin = LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 59, 59, 999));
		List<Token> listaTokenVigentes = tokenRepository.findByVigenteActivo(inicio, fin, true);
		listaTokenVigentes = this.invalidarListaToken(listaTokenVigentes);
		
		
		caducidad = LocalDateTime.of(LocalDate.now(), LocalTime.now());
		caducidad = caducidad.plusMinutes(5);
		
		token = new Token();
		token.setEmpleado(empleado);
		token.setCaducidad(caducidad);
		token.setToken(securityTool.getRandomString());
		token.setValido(true);
		
		token = tokenRepository.save(token);
		
		
		return token;
	}
	
	private List<Token> invalidarListaToken(List<Token> listaToken) {
		for(Token token : listaToken) {
			token.setValido(false);
		}
		listaToken = (List<Token>)tokenRepository.saveAll(listaToken);
		
		return listaToken;
	}
	
	

}
