package com.example.pollEveryWhere.pollEveryWhere.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.context.annotation.Primary;

@Entity
public class Poll {
	 @Id 
	 @GeneratedValue( strategy=GenerationType.IDENTITY )
	 private int Pid;
     private String question;
     private int yes;
     private int no;
     private String yesQuestion;
     private String noQuestion;
     private String secret;
     private String radio;
     private String date;
     private String noPercent;
     private String yesPercent;
     @ManyToOne(targetEntity=User.class)
    // @JoinColumn(name = "SCHOOL_ID", table = "SCHOOL")
     private User user;
	public Poll(String question, int yes, int no, String yesQuestion, String noQuestion, String secret, String radio,
			String date, User user) {
		super();
		this.question = question;
		this.yes = yes;
		this.no = no;
		this.yesQuestion = yesQuestion;
		this.noQuestion = noQuestion;
		this.secret = secret;
		this.radio = radio;
		this.date = date;
		this.user = user;
	}
	public int getPid() {
		return Pid;
	}
	public void setPid(int pid) {
		Pid = pid;
	}
	public Poll() {
		// TODO Auto-generated constructor stub
	}
	
	public Poll(String question, int yes, int no, String yesQuestion, String noQuestion, String secret, String radio,
			String date, String noPercent, String yesPercent, User user) {
		super();
		this.question = question;
		this.yes = yes;
		this.no = no;
		this.yesQuestion = yesQuestion;
		this.noQuestion = noQuestion;
		this.secret = secret;
		this.radio = radio;
		this.date = date;
		this.noPercent = noPercent;
		this.yesPercent = yesPercent;
		this.user = user;
	}
	@Override
	public String toString() {
		return "Poll [Pid=" + Pid + ", question=" + question + ", yes=" + yes + ", no=" + no + ", yesQuestion="
				+ yesQuestion + ", noQuestion=" + noQuestion + ", secret=" + secret + ", radio=" + radio + ", date="
				+ date + ", noPercent=" + noPercent + ", yesPercent=" + yesPercent + ", user=" + user + "]";
	}
	public String getNoPercent() {
		return noPercent;
	}
	public void setNoPercent(String noPercent) {
		this.noPercent = noPercent;
	}
	public String getYesPercent() {
		return yesPercent;
	}
	public void setYesPercent(String yesPercent) {
		this.yesPercent = yesPercent;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public int getYes() {
		return yes;
	}
	public void setYes(int yes) {
		this.yes = yes;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getYesQuestion() {
		return yesQuestion;
	}
	public void setYesQuestion(String yesQuestion) {
		this.yesQuestion = yesQuestion;
	}
	public String getNoQuestion() {
		return noQuestion;
	}
	public void setNoQuestion(String noQuestion) {
		this.noQuestion = noQuestion;
	}
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	public String getRadio() {
		return radio;
	}
	public void setRadio(String radio) {
		this.radio = radio;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User i) {
		this.user = i;
	}

}
