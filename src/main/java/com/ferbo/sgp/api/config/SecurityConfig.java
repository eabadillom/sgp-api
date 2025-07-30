package com.ferbo.sgp.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ferbo.sgp.api.tool.SistemaDetailsSrv;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)	
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private SistemaDetailsSrv sistemaDetailsSrv;
	
	@Override
	protected void configure (AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(sistemaDetailsSrv)
			.passwordEncoder(encoder())
			;
	}
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
            .anyRequest().authenticated()
            .and()
            .httpBasic();
    }

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth
//			.inMemoryAuthentication()
//			.withUser("ferbo")
//			.password(encoder().encode("ferbogestionpasswd"))
//			.roles("ADMIN")
//			.and()
//			.withUser("user")
//			.password(encoder().encode("password"))
//			.roles("USER")
//			;
//	}
	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http
//			.csrf().disable()
//			.authorizeRequests()
//			.anyRequest().authenticated()
//			.and()
//			.httpBasic();
//	}

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
}