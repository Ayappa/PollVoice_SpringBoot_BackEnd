package com.example.pollEveryWhere.pollEveryWhere.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class PollTrack {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int PtId;
   @ManyToOne(targetEntity=User.class)
   private User user;
   @ManyToOne(targetEntity=Poll.class)
   private Poll poll;
   
   public PollTrack(){}
public PollTrack( User user, Poll poll) {
	super();
	this.user = user;
	this.poll = poll;
}
public int getPtId() {
	return PtId;
}
public void setPtId(int ptId) {
	PtId = ptId;
}
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}
public Poll getPoll() {
	return poll;
}
public void setPoll(Poll poll) {
	this.poll = poll;
}
@Override
public String toString() {
	return "PollTrack [PtId=" + PtId + ", user=" + user + ", poll=" + poll + "]";
}

   
}
