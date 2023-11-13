package com.example.demo.model;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "Admin")
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer adminId;
	private String name;
	private String surname;
	private String email;
	private String telephonenumber;
	private String password;
	
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public Admin(Integer adminId, String name, String surname, String email, String telephonenumber, String password) {
		super();
		this.adminId = adminId;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.telephonenumber = telephonenumber;
		this.password = password;
	}



	public Integer getAdminId() {
		return adminId;
	}



	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephonenumber() {
		return telephonenumber;
	}

	public void setTelephonenumber(String telephonenumber) {
		this.telephonenumber = telephonenumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	
}
