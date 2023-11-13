package com.example.demo.controller;

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

import com.example.demo.model.Admin;
import com.example.demo.repository.AdminRepository;

@RestController
@CrossOrigin("*")
public class AdminController {
	
	@Autowired
	AdminRepository adminRepository;
	
	@GetMapping("/admin")
	public ResponseEntity<Object> getAdmin() {
		try {
			List<Admin> admins = adminRepository.findAll();
			return new ResponseEntity<>(admins, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/admin")
	public ResponseEntity<Object> addAdmin(@RequestBody Admin body) {
		
		try {		
			Admin admin =  adminRepository.save(body);
			return new ResponseEntity<>(admin, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		
	}

	@GetMapping("/admin/{adminId}")
	public ResponseEntity<Object> getAdminDetail(@PathVariable Integer adminId) {

		try {		
			Optional<Admin> admin = adminRepository.findById(adminId);
			if(admin.isPresent()) {
				return new ResponseEntity<>(admin, HttpStatus.OK);
			}else {
				return new ResponseEntity<>("Admin not found", HttpStatus.BAD_REQUEST);
			}
					
		} catch (Exception e) {
			return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	@PutMapping("admin/{adminId}")
	public ResponseEntity<Object> updateAdmin(@PathVariable Integer adminId, @RequestBody Admin body) {

		try {
			Optional<Admin> admin = adminRepository.findById(adminId);

			if (admin.isPresent()) {
				Admin adminEdit = admin.get();
				adminEdit.setAdminId (body.getAdminId());
				adminEdit.setName(body.getName());
				adminEdit.setSurname(body.getSurname());
				adminEdit.setEmail(body.getEmail());
				adminEdit.setTelephonenumber(body.getTelephonenumber());
				adminRepository.save(adminEdit);

				return new ResponseEntity<>(adminEdit, HttpStatus.OK);
			} else {
				return new ResponseEntity<>("admin not found", HttpStatus.BAD_REQUEST);
			}
			
		} catch (Exception e) {
			return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	@DeleteMapping("admin/{id}")
	public ResponseEntity<Object> deleteAdmin(@PathVariable Integer adminId) {
	    try {
	        // Check if the admin with the given ID exists in the database
	        Optional<Admin> admin = adminRepository.findById(adminId);

	        if (admin.isPresent()) {
	            // If the admin exists, delete it from the database
	            adminRepository.delete(admin.get());

	            // Return a success response
	            return new ResponseEntity<>("Delete Successful", HttpStatus.OK);
	        } else {
	            // If the admin doesn't exist, return a bad request response
	            return new ResponseEntity<>("Admin not found", HttpStatus.BAD_REQUEST);
	        }
	    } catch (Exception e) {
	        // Handle any internal server error and return an appropriate response
	        return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	
	@PostMapping("/adminLogin")
	public ResponseEntity<Object> loginAdmin(@RequestBody Admin body) {
		Admin admin =  adminRepository.findByEmail(body.getEmail());
		
		if(admin == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Studeent not found.");
		}
		
		if (!admin.getPassword().equals(body.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(admin);
		
	}

}
