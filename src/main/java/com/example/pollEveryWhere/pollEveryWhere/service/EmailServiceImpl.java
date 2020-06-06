package com.example.pollEveryWhere.pollEveryWhere.service;

import java.security.GeneralSecurityException;
import java.util.Properties;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;
import com.sun.mail.util.MailSSLSocketFactory;

@Configuration 
public class EmailServiceImpl  {

    @Autowired
    public JavaMailSender emailSender;
    Session session ;
    
    @Bean
    public JavaMailSender javaMailService() {
    	 JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    	    mailSender.setHost("smtp.gmail.com");
    	    mailSender.setPort(587);
    	    mailSender.setUsername("pollVoice2020@gmail.com");
    	    mailSender.setPassword("PollService@2020*");  
    	    Properties props = mailSender.getJavaMailProperties();
    	    props.put("mail.transport.protocol", "smtp");
    	    props.put("mail.smtp.auth", "true");
    	    props.put("mail.smtp.starttls.enable", "true");
    	    props.put("mail.debug", "true");
    	    props.put("mail.smtp.ssl.trust", "smtp.gmail.com");    	     
    	    return mailSender;
    }
    
  
    public boolean sendSimpleMessage(String to, String subject, String text) {
    	        SimpleMailMessage message = new SimpleMailMessage(); 
    	        message.setTo(to); 
    	        message.setSubject(subject); 
    	        message.setText(text);
    	        try{
    	        emailSender.send(message);
    	        return true;
    	        }catch(Exception e) {
    	        	return false;
    	        }
    	   
    	    }
}
