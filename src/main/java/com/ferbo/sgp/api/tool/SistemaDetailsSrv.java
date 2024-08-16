package com.ferbo.sgp.api.tool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ferbo.sgp.api.model.Sistema;
import com.ferbo.sgp.api.repository.SistemaRepo;

@Service
public class SistemaDetailsSrv implements UserDetailsService {
	
	private static Logger log = LogManager.getLogger(SistemaDetailsSrv.class);
	
	@Autowired
	private SistemaRepo sistemaRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails user = null;
		Sistema sistema = null;
		try {
			sistema = sistemaRepo.findByNombre(username);
			log.info("Usuario: {}", sistema);
			user = User.withUsername(sistema.getNombre())
			.password(sistema.getPassword())
			.roles(sistema.getRol())
			.build();
		} catch(Exception ex) {
			log.error("Problema para extraer el usuario: " + username, ex);
		}
		return user;
	}
}
