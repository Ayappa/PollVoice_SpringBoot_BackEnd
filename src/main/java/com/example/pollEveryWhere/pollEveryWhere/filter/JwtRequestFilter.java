package com.example.pollEveryWhere.pollEveryWhere.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.preauth.websphere.WebSpherePreAuthenticatedWebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.NestedServletException;

import com.example.pollEveryWhere.pollEveryWhere.jwt.JwtUtil;
import com.example.pollEveryWhere.pollEveryWhere.model.User;
import com.example.pollEveryWhere.pollEveryWhere.repository.UserRepository;
import com.example.pollEveryWhere.pollEveryWhere.service.UserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;

@CrossOrigin(origins = "http://localhost:3000")
@Component
public class JwtRequestFilter extends OncePerRequestFilter{
	@Autowired
	UserService userservice;
	@Autowired
	UserRepository userRepo;
	@Autowired 
	private JwtUtil jwt;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException, ExpiredJwtException,NullPointerException ,IllegalStateException,NestedServletException {
		String authToken=request.getHeader("Authorization");
		String email=null;
		String token=null;
		if(authToken!=null && authToken.startsWith("Bearer ")) {
			token=authToken.substring(7);
			try {
				Claims claim=jwt.extractAllClaims(token);
			    email=(String) claim.get("email");			
				if(email!=null) {
					User userDetail=userRepo.findByEmail(email);
					if( jwt.validToken(token,userDetail)&& SecurityContextHolder.getContext().getAuthentication() == null) {
						UserDetails user=userservice.loadUserByUsername(email);
						UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=
								new UsernamePasswordAuthenticationToken(user.getUsername(), null,user.getAuthorities());
						usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource()
								.buildDetails(request));
						SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);		
	
					}else {
						throw new Exception();
					}
				}
			}catch ( Exception e ) {
					// response.setStatus(403);
					 response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
					 response.getOutputStream().print("invalid");
					 response.flushBuffer();
					 return ;		        
				}
			
		}
		filterChain.doFilter(request, response);
	}

}
