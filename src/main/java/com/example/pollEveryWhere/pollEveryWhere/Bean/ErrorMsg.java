package com.example.pollEveryWhere.pollEveryWhere.Bean;

public class ErrorMsg {
private String errorMsg;

public ErrorMsg(String errorMsg) {
	super();
	this.errorMsg = errorMsg;
}

public String getErrorMsg() {
	return errorMsg;
}

public void setErrorMsg(String errorMsg) {
	this.errorMsg = errorMsg;
}

@Override
public String toString() {
	return "ErrorMsg [errorMsg=" + errorMsg + "]";
}

}
