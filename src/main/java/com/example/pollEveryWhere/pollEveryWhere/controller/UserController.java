package com.example.pollEveryWhere.pollEveryWhere.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import com.example.pollEveryWhere.pollEveryWhere.jwt.JwtBean;
import com.example.pollEveryWhere.pollEveryWhere.jwt.JwtUtil;
import com.example.pollEveryWhere.pollEveryWhere.model.User;
import com.example.pollEveryWhere.pollEveryWhere.repository.UserRepository;
import com.example.pollEveryWhere.pollEveryWhere.service.EmailServiceImpl;
import com.example.pollEveryWhere.pollEveryWhere.service.UserService;

import io.jsonwebtoken.Claims;

@RestController
@CrossOrigin(origins = { "http://localhost:3000","https://pollvoicefrontend.herokuapp.com"})
public class UserController {
	@Autowired
	UserService userservice;
    @Autowired
    EmailServiceImpl emailServiceImpl;
	@Autowired
	UserRepository userRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AuthenticationManager authenticationManagerBean;
	@Autowired 
	private JwtUtil jwt;

	@PostMapping("/registerUser")
	public JwtBean welcome(@RequestBody User user) {
		System.out.println("inside Register");
		User userCheck=userservice.findUserByEmail(user.getEmail());
		if(userCheck!=null) {
			final JwtBean jwtToken=new JwtBean("alreadyExists");
			return jwtToken;
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		System.out.println(user.toString());
		 User userDetails= userservice.saveUser(user);
		 String toekn=jwt.generateToken(userDetails);
			final JwtBean jwtToken=new JwtBean(toekn);
			//String link="http://localhost:3000/sendRegisterMail/"+toekn;
			String link="https://pollvoicefrontend.herokuapp.com/sendRegisterMail/"+toekn;
			emailServiceImpl.sendSimpleMessage(user.getEmail(),"Registration","\"click on the link to login \\n "+link);
			return jwtToken;
	}
	
	@PostMapping("/updatePassword")
	public boolean updatePassword(@RequestBody User user,@RequestHeader("Authorization") String authToken) {
		System.out.println("updatePassword");
		
	
		Claims claim=jwt.extractAllClaims(authToken);
	    String email=(String) claim.get("email");		
		User userCheck=userservice.findUserByEmail(email);
		System.out.println(jwt.validToken(authToken, userCheck));
		if(!jwt.validToken(authToken, userCheck)) {
			final JwtBean jwtToken=new JwtBean("alreadyExists");
			return false;
		}else {
		//User userCheck=userservice.findUserByEmail(user.getEmail());
			userCheck.setPassword(passwordEncoder.encode(user.getPassword()));
			userservice.saveUser(userCheck);
			 return true;	
		}
	}
	
	@PostMapping("/updatePasswordMail")
	public String upDatePassword(@RequestBody User user) {
			User userCheck=userservice.findUserByEmail(user.getEmail());
			if(userCheck==null) {
				final JwtBean jwtToken=new JwtBean("alreadyExists");
				return "user dont exists";
			}
		String toekn=jwt.generateDummyToken(userCheck);
		//String link="http://localhost:3000/updatePasswordMail/"+toekn;
		String link="https://pollvoicefrontend.herokuapp.com/updatePasswordMail/"+toekn;
		boolean status=emailServiceImpl.sendSimpleMessage(user.getEmail(),"Update PAssword","\"click on the link to reset password : \\n "+link);
		//emailServiceImpl.sendSimpleMessage(user.getEmail(),"Update PAssword","\"click on link to reset password : \\n Spring Boot Email\"");
		return status?"email sent":"try again later";
	}
	
	@PostMapping("/checkToken")
	public boolean checkToken(@RequestHeader("Authorization") String authToken) {
		String token=authToken.substring(7);
       return jwt.isTokenExpired(token);
		
	}
	
	@PostMapping("/loginUser")
	public JwtBean welcomeCheck(@RequestBody User user) throws Exception {
		
		User userCheck=userservice.findUserByEmail(user.getEmail());
		if(userCheck==null) {
			final JwtBean jwtToken=new JwtBean("user dont exists");
			return jwtToken;
		}
		System.out.println("in check request ="+ user.toString() );
		try {
		authenticationManagerBean.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword()));
		}
		catch(BadCredentialsException e) {
			final JwtBean jwtToken=new JwtBean("Incorrect creditional");
			return jwtToken;
			//throw new Exception("Incorrect creditional",e);
		}
		User userDetails=userRepo.findByEmail(user.getEmail());
		String toekn=jwt.generateToken(userDetails);
		final JwtBean jwtToken=new JwtBean(toekn);
		return jwtToken;
	}
//	spring.mail.username=PollVoice2020@gmail.com
//			spring.mail.password=PollService@2020!
	@GetMapping("/sendRegisterMail")
	public String sendRegisterMail() {
		boolean status=emailServiceImpl.sendSimpleMessage("kaikai9500@gmail.com","Testing from Spring Boot","\"Hello World \\n Spring Boot Email\"");
		return status?"email sent":"try again later";
	}
	
}
