package com.ferbo.sgp.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.ferbo.sgp.api.model.Sistema;
import com.ferbo.sgp.api.repository.SistemaRepo;

@Component
public class DataLoader implements CommandLineRunner  {
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired
	private SistemaRepo sistemaRepo;
	
	@Override
	public void run(String... args) {
		//this.agregarUsuarios();
	}
	
	private void agregarUsuarios() {
		Sistema s = null;
		
		s = new Sistema();
		s.setNombre("GESTION");
		s.setPassword(passwordEncoder.encode("78432HJIw"));
		s.setRol("SYSTEM");
		
		sistemaRepo.save(s);
		
		s = new Sistema();
		s.setNombre("PLANTA1");
		s.setRol("FP_CLIENT");
		s.setPassword(passwordEncoder.encode("abc123@"));
		
		sistemaRepo.save(s);
	}

}
