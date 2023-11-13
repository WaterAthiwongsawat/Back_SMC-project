package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Reserveroom;

@Repository
public interface ReserveroomRepository extends JpaRepository<Reserveroom, Integer>{

	List<Reserveroom> findByStudentId(String studentId);



}