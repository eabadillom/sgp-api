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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.ferbo.sgp.api.filter.JwtAuthenticationFilter;
import org.springframework.security.authentication.AuthenticationManager;

import com.ferbo.sgp.api.tool.SistemaDetailsSrv;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)	
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private SistemaDetailsSrv sistemaDetailsSrv;

	@Autowired
	private JwtAuthenticationFilter jwtAtuthenticationFilter;
	
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
                // Rutas protegidas con BASIC
                .antMatchers("/fp-client/**").authenticated()
                .antMatchers("/movil/inicio").authenticated()

                // Rutas protegidas con JWT
                .antMatchers("/inicio/**").authenticated()

                // Otras rutas (public, etc.)
                .anyRequest().permitAll()
            .and()
            .httpBasic() // Habilita Basic Auth
            .and()
            // Agrega filtro JWT antes del filtro de login
            .addFilterBefore(jwtAtuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }

	/* 
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
            .anyRequest().authenticated()
            .and()
            .httpBasic();
    }*/

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

	@Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}