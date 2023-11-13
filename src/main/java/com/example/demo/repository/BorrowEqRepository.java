package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.BorrowEq;

@Repository
public interface BorrowEqRepository extends JpaRepository<BorrowEq, Integer> {

	List<BorrowEq> findByStudentId(String studentId);


}
