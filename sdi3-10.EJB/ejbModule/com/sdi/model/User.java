package com.sdi.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.crypto.Data;

/**
 * This class is not an entity, it is a DTO with the same fields as a row in the
 * table
 * 
 * @see Data Transfer Object pattern
 * @author alb
 *
 */
@XmlRootElement
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String login;
	private String password;
	private String name;
	private String surname;
	private String email;
	private String state;
	
	private UserStatus status;
	
	public User(){}
	
	public User(String name, String surname, String password,
			String login, String email) {
		super();
		this.name = name;
		this.surname = surname;
		this.password = password;
		this.login = login;
		this.email = email;
	}
	@XmlElement
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	@XmlElement
	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}
	@XmlElement
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@XmlElement
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	@XmlElement
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	@XmlElement
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@XmlElement
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	

	@Override
	public String toString() {
		return "User [id=" + id 
				+ ", login=" + login 
				+ ", password=" + password 
				+ ", name=" + name 
				+ ", surname=" + surname 
				+ ", status=" + status 
				+ ", email=" + email
			+ "]";
	}
	@XmlElement
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}


}
