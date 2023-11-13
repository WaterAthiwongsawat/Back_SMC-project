package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;

@RestController
@CrossOrigin("*")
public class StudentController {

	@Autowired
	StudentRepository studentRepository;
    
	
	@GetMapping("/students")
	public ResponseEntity<Object> getStudent() {
     try {		
	     List<Student> students = studentRepository.findAll(); 	
		 return new ResponseEntity<>(students, HttpStatus.OK);
	} catch (Exception e) {	
		return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	}

	@PostMapping("/students")
	public ResponseEntity<Object> addStudent(@RequestBody Student body) {
		
		try {		
			Student student =  studentRepository.save(body);
			return new ResponseEntity<>(student, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		
	}

	@GetMapping("/student/{studentId}")
	public ResponseEntity<Object> getStudentDetail(@PathVariable Long studentId) {

		try {		
			Optional<Student> student = studentRepository.findById(studentId);
			if(student.isPresent()) {
				return new ResponseEntity<>(student, HttpStatus.OK);
			}else {
				return new ResponseEntity<>("Student not found", HttpStatus.BAD_REQUEST);
			}
					
		} catch (Exception e) {
			return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}

	@PutMapping("student/{id}")
	public ResponseEntity<Object> updateStudent(@PathVariable Integer id, @RequestBody Student body) {

		try {
			Optional<Student> student = studentRepository.findById(id);

			if (student.isPresent()) {
				Student studentEdit = student.get();
				studentEdit.setStudentId(body.getStudentId());
				studentEdit.setName(body.getName());
				studentEdit.setSurname(body.getSurname());
				studentEdit.setEmail(body.getEmail());
				studentEdit.setTelephonenumber(body.getTelephonenumber());
				studentRepository.save(studentEdit);

				return new ResponseEntity<>(studentEdit, HttpStatus.OK);
			} else {
				return new ResponseEntity<>("student not found", HttpStatus.BAD_REQUEST);
			}
			
		} catch (Exception e) {
			return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	@DeleteMapping("student/{id}")
	public ResponseEntity<Object> deletestudent(@PathVariable Integer id) {
	    try {
	        // Check if the student with the given ID exists in the database
	        Optional<Student> student = studentRepository.findById(id);

	        if (student.isPresent()) {
	            // If the student exists, delete it from the database
	            studentRepository.delete(student.get());

	            // Return a success response
	            return new ResponseEntity<>("Delete Successful", HttpStatus.OK);
	        } else {
	            // If the student doesn't exist, return a bad request response
	            return new ResponseEntity<>("Student not found", HttpStatus.BAD_REQUEST);
	        }
	    } catch (Exception e) {
	        // Handle any internal server error and return an appropriate response
	        return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	

	
	@PostMapping("/login")
	public ResponseEntity<Object> loginStudent(@RequestBody Student body) {
		Student student =  studentRepository.findByEmail(body.getEmail());
		
		if(student == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Studeent not found.");
		}
		
		if (!student.getPassword().equals(body.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(student);
		
	}

}