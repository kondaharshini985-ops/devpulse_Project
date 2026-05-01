package com.example.securityconfig;


import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

@Component
public class jwtUtil {

	@org.springframework.beans.factory.annotation.Value("${jwt.secret}")
	private String secret;
	@org.springframework.beans.factory.annotation.Value("${jwt.expiration}")
	private Long expiration;
	
	public String generateToken(String email) {
		return Jwts.builder()
				.setSubject(email)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis()+expiration))
				.signWith(getSignKey(), SignatureAlgorithm.HS256)
				.compact();
		
	}
	
	public String extractEmailFromToken(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(getSignKey())
				.build()
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
		
	}
	
	public boolean isValidToken(String token) {
	
		try{
			Jwts.parserBuilder()
		
		.setSigningKey(getSignKey())
		.build()
		.parseClaimsJws(token);
		
		return true;
		}
		catch(Exception e) {
			return false;
		}
			
	
	}
	public Key getSignKey() {
		return Keys.hmacShaKeyFor(secret.getBytes());
	}
	
}
