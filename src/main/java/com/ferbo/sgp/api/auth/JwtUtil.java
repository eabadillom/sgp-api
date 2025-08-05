package com.ferbo.sgp.api.auth;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

/**
 *
 * @author alberto
 */
@Component
public class JwtUtil {

    private static final Logger log = LogManager.getLogger(JwtUtil.class);

    private String secret;

    @Value("${jwt.accessExpiration}")
    private long accessExpiration;
    
    @Value("${jwt.refreshExpiration}")
    private long refreshExpiration;

    @PostConstruct
    public void init() {
        try {
            InitialContext ctx = new InitialContext();
            secret = (String) ctx.lookup("java:comp/env/jwt.secret");
        } catch (NamingException e) {
            log.info("No se pude extraer del JNDI el secreto. {}", e);
            throw new RuntimeException("No se pudo cargar desde el JNDI");
        }
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + accessExpiration))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateRefreshToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + refreshExpiration))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secret.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean isValid(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(secret.getBytes()).build().parseClaimsJws(token);
            return true;
        } catch (JwtException jwte) {
            return false;
        }
    }
}
