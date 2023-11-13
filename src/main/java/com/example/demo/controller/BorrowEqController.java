package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.BorrowEq;
import com.example.demo.model.Reserveroom;
import com.example.demo.repository.BorrowEqRepository;

@RestController
@CrossOrigin("*")
public class BorrowEqController {
	
	@Autowired
	BorrowEqRepository borrowEqRepository;
	
    @GetMapping("/borrowHistory")
    public ResponseEntity<Object> getHistory() {
        try {
            List<BorrowEq> borrowEq = borrowEqRepository.findAll();
            return new ResponseEntity<>(borrowEq, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/borrowHistory/{studentId}")
    public ResponseEntity<Object> getHistoryByStudent(@PathVariable String studentId) {
        try {
            // Assuming you have a method in reserveroomRepository to find by student_id
            List<BorrowEq> borrowEqs = borrowEqRepository.findByStudentId(studentId);
            
            return new ResponseEntity<>(borrowEqs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@PostMapping("/borroeEq")
	public ResponseEntity<Object> borrowEq(@RequestBody BorrowEq body) {
	
	try {
		BorrowEq borrowEq =  borrowEqRepository.save(body);
		return new ResponseEntity<>(borrowEq, HttpStatus.CREATED);
	} catch (Exception e) {
		e.printStackTrace();
		return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	}
	
	@PutMapping("/reBorrow/{borrow_id}")
	public ResponseEntity<Object> updateRemaining(@PathVariable Integer borrow_id) {
	    try {
	        Optional<BorrowEq> optionalBorrow = borrowEqRepository.findById(borrow_id);
	        
	        if (optionalBorrow.isPresent()) {
	            BorrowEq borrow = optionalBorrow.get();
	            borrow.setBorrow(false);
	            borrowEqRepository.save(borrow);
	            return new ResponseEntity<>("Successfully updated isBorrow to false", HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>("Borrow record not found", HttpStatus.NOT_FOUND);
	        }
	    } catch (Exception e) {
	        return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}


}
