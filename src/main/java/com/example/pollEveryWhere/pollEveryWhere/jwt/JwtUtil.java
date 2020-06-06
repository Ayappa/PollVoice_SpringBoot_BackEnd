package com.example.pollEveryWhere.pollEveryWhere.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.cglib.core.internal.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.example.pollEveryWhere.pollEveryWhere.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@Component
public class JwtUtil {
    private String SECRET_KEY="Sercret";
    
    public String extractUserId(String token) {
    	return extractClaim(token,Claims::getSubject);
    }
    public Date extactExpiration(String token) {
    	return extractClaim(token,Claims::getExpiration);
    }
    
    
    public <T> T extractClaim(String token, Function<Claims,T> claimResolver) {
		// TODO Auto-generated method stub
    	final Claims claims=extractAllClaims(token);
		return claimResolver.apply(claims);
	}
	public Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	}
    
	public String generateToken(User user) {
		Map<String,Object> claims=new HashMap<>();
		claims.put("email",user.getEmail());
		System.out.println("in generate token "+user.toString());
		 return createToken(claims,user.getuId());
	}

	public String createToken(Map<String, Object> claims, int getuId) {
		return Jwts.builder().setClaims(claims).setSubject(String.valueOf(getuId)).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*60*60*60))
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
	}
	
	public String generateDummyToken(User user) {
		Map<String,Object> claims=new HashMap<>();
		claims.put("email",user.getEmail());
		 return createToken(claims,user.getuId());
	}
	
	public String createDummyToken(Map<String, Object> claims, int getuId) {
		return Jwts.builder().setClaims(claims).setSubject(String.valueOf(getuId)).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*10))
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
	}
	
	public Boolean validToken(String token,User user) {
		final String uId=extractUserId(token);
		return (uId.equals(String.valueOf(user.getuId())) && !isTokenExpired(token));
	}

	public boolean isTokenExpired(String token) {
		// TODO Auto-generated method stub
		return extactExpiration(token).before(new Date());
	}
	
	
}
