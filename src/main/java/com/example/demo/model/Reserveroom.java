package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "reserveroom")
public class Reserveroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reserve_id;
    private String reserve_date;
    private String reserve_time;
    private Integer room_id;
    private String studentId;


	public Reserveroom() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reserveroom(Integer reserve_id, String reserve_date, String reserve_time, Integer room_id, Student student, String student_id) {
		super();
		this.reserve_id = reserve_id;
		this.reserve_date = reserve_date;
		this.reserve_time = reserve_time;
		this.room_id = room_id;
		this.studentId = student_id;
	}

	public Integer getReserve_id() {
		return reserve_id;
	}

	public void setReserve_id(Integer reserve_id) {
		this.reserve_id = reserve_id;
	}

	public String getReserve_date() {
		return reserve_date;
	}

	public void setReserve_date(String reserve_date) {
		this.reserve_date = reserve_date;
	}

	public String getReserve_time() {
		return reserve_time;
	}

	public void setReserve_time(String reserve_time) {
		this.reserve_time = reserve_time;
	}

	public Integer getRoom_id() {
		return room_id;
	}

	public void setRoom_id(Integer room_id) {
		this.room_id = room_id;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}



   

	
    
}