package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "BorrowEq")
public class BorrowEq {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer borrow_id;
    private String borrow_date;
    private String borrow_time;
    private Integer EqId;
    private String studentId;
    private boolean isBorrow;
    
	public BorrowEq() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BorrowEq(Integer borrow_id, String borrow_date, String borrow_time, Integer eqId, String studentId,
			boolean isBorrow) {
		super();
		this.borrow_id = borrow_id;
		this.borrow_date = borrow_date;
		this.borrow_time = borrow_time;
		EqId = eqId;
		this.studentId = studentId;
		this.isBorrow = isBorrow;
	}

	public Integer getBorrow_id() {
		return borrow_id;
	}

	public void setBorrow_id(Integer borrow_id) {
		this.borrow_id = borrow_id;
	}

	public String getBorrow_date() {
		return borrow_date;
	}

	public void setBorrow_date(String borrow_date) {
		this.borrow_date = borrow_date;
	}

	public String getBorrow_time() {
		return borrow_time;
	}

	public void setBorrow_time(String borrow_time) {
		this.borrow_time = borrow_time;
	}

	public Integer getEqId() {
		return EqId;
	}

	public void setEqId(Integer eqId) {
		EqId = eqId;
	}

	public String getstudentId() {
		return studentId;
	}

	public void setstudentId(String studentId) {
		this.studentId = studentId;
	}

	public boolean isBorrow() {
		return isBorrow;
	}

	public void setBorrow(boolean isBorrow) {
		this.isBorrow = isBorrow;
	}
    
    

}
