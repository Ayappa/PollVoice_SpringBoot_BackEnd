package com.example.pollEveryWhere.pollEveryWhere.jwt;

public class JwtBean {
   private String token;

public JwtBean(String token) {
	super();
	this.token = token;
}

public String getToken() {
	return token;
}

public void setToken(String token) {
	this.token = token;
}
}
