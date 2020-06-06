package com.example.pollEveryWhere.pollEveryWhere.Bean;

public class ResponseBean {
  private String msg;
  private String yes;
  private String no;
  private String totalResponse;
public String getMsg() {
	return msg;
}
@Override
public String toString() {
	return "ResponseBean [msg=" + msg + ", yes=" + yes + ", no=" + no + ", totalResponse=" + totalResponse + "]";
}
public String getYes() {
	return yes;
}
public void setYes(String yes) {
	this.yes = yes;
}
public String getNo() {
	return no;
}
public void setNo(String no) {
	this.no = no;
}
public String getTotalResponse() {
	return totalResponse;
}
public void setTotalResponse(String totalResponse) {
	this.totalResponse = totalResponse;
}
public void setMsg(String msg) {
	this.msg = msg;
}

public ResponseBean(String msg, String yes, String no, String totalResponse) {
	super();
	this.msg = msg;
	this.yes = yes;
	this.no = no;
	this.totalResponse = totalResponse;
}
public ResponseBean(){}


}
