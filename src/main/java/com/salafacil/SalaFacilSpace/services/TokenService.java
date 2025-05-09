package com.salafacil.SalaFacilSpace.services;

import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenService {

	private static final String SECRET = "chave-secreta-top";

	
	public String obterUsernameDoToken(String token) {
	    return Jwts.parser()
	            .setSigningKey(SECRET)
	            .parseClaimsJws(token)
	            .getBody()
	            .getSubject(); // O "subject" do token é o username
	}

    public String gerarToken(UserDetails user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 dia
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }
    
    public boolean validarToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
