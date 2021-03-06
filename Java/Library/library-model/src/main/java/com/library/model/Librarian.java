package com.library.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Librarian {

	private String username;
	private String password;
	private String emailID;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

}