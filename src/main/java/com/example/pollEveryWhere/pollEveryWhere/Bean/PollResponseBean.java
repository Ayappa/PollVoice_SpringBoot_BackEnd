package com.example.pollEveryWhere.pollEveryWhere.Bean;


public class PollResponseBean {
   public int questionId;
   public int response;

public String toString() {
	return "PollResponseBean [questionId=" + questionId + ", response=" + response + "]";
}
public int getQuestionId() {
	return questionId;
}
public void setQuestionId(int questionId) {
	this.questionId = questionId;
}
public int getResponse() {
	return response;
}
public void setResponse(int response) {
	this.response = response;
}
public PollResponseBean(int questionId, int response) {
	super();
	this.questionId = questionId;
	this.response = response;
}
}
