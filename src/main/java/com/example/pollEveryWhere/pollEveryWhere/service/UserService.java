package com.example.pollEveryWhere.pollEveryWhere.service;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.pollEveryWhere.pollEveryWhere.model.User;
import com.example.pollEveryWhere.pollEveryWhere.repository.UserRepository;

@Service
public class UserService  implements  UserDetailsService {
   
	@Autowired
	private UserRepository userRepository;

	public User saveUser(User user) {
		System.out.println("in service");
		// TODO Auto-generated method stub
		return userRepository.save(user);	
		}
	
	public User findUserByEmail(String email) {
		//System.out.println("in service");
		// TODO Auto-generated method stub
		return userRepository.findByEmail(email);	
		}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("in load by username= "+username);
		User user=userRepository.findByEmail(username);
		System.out.println("in load by username= "+user);

//		MyUserDetails UserDetails = new MyUserDetails(username);
//		//System.out.println("in load by UserDetails= "+username);

		

		return new org.springframework.security.core.userdetails.User(user.getEmail(),
		                                                              user.getPassword(),
		                                                              Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
		
		//return UserDetails;
	}


	
}
