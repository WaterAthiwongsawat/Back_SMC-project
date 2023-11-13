package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.TinyBitSet;
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

import com.example.demo.model.Equipment;
import com.example.demo.model.Reserveroom;
import com.example.demo.repository.EquipmentRepository;

@CrossOrigin("*")
@RestController
public class EquipmentController {
	
	@Autowired
	EquipmentRepository equipmentRepository;
	
	 @GetMapping("/Equipment")
	    public ResponseEntity<Object> getEquipment() {
	        try {
	            List<Equipment> equipment = equipmentRepository.findAll();
	            return new ResponseEntity<>(equipment, HttpStatus.OK);
	        } catch (Exception e) {
	            return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	 
	 @GetMapping("/Equipment/{EqId}")
	 public ResponseEntity<Object> getEqDetail(@PathVariable Integer EqId){

			try {
				Optional<Equipment> equipment = equipmentRepository.findById(EqId);
				if(equipment.isPresent()) {
					return new ResponseEntity<>(equipment, HttpStatus.OK);
				}
				return new ResponseEntity<>("equipment Not Found", HttpStatus.BAD_REQUEST);
			} catch (Exception e) {
				return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	 
	 @PostMapping("/Equipment")
	 public ResponseEntity<Object> addReserve(@RequestBody Equipment body) {
	 	
	 	try {
	 		Equipment equipment =  equipmentRepository.save(body);
	 		return new ResponseEntity<>(equipment, HttpStatus.CREATED);
	 	} catch (Exception e) {
	 		e.printStackTrace();
	 		return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
	 	}
	 	}
	 
	 @PutMapping("/updateRemaining/{EqId}")
	 public ResponseEntity<Object> updateRemaining(@PathVariable Integer EqId) {
	     try {
	         Optional<Equipment> optionalEquipment = equipmentRepository.findById(EqId);

	         if (optionalEquipment.isPresent()) {
	             Equipment equipment = optionalEquipment.get();
	             int currentRemaining = equipment.getRemaining();
	             
	             if (currentRemaining > 0) {
	                 equipment.setRemaining(currentRemaining - 1);
	                 equipmentRepository.save(equipment);
	                 return new ResponseEntity<>("Remaining updated successfully", HttpStatus.OK);
	             } else {
	                 return new ResponseEntity<>("No remaining items available", HttpStatus.BAD_REQUEST);
	             }
	         } else {
	             return new ResponseEntity<>("Equipment not found", HttpStatus.NOT_FOUND);
	         }
	     } catch (Exception e) {
	         return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
	     }
	 }
	 
	 @PutMapping("/reupdateRemaining/{EqId}")
	 public ResponseEntity<Object> reUpdateRemaining(@PathVariable Integer EqId) {
	     try {
	         Optional<Equipment> optionalEquipment = equipmentRepository.findById(EqId);

	         if (optionalEquipment.isPresent()) {
	             Equipment equipment = optionalEquipment.get();
	             int currentRemaining = equipment.getRemaining();
	             int allQuantity = equipment.getQuantity();
	             
	             if (currentRemaining < allQuantity) {
	                 equipment.setRemaining(currentRemaining + 1);
	                 equipmentRepository.save(equipment);
	                 return new ResponseEntity<>("Remaining updated successfully", HttpStatus.OK);
	             } else {
	                 return new ResponseEntity<>("items available", HttpStatus.BAD_REQUEST);
	             }
	         } else {
	             return new ResponseEntity<>("Equipment not found", HttpStatus.NOT_FOUND);
	         }
	     } catch (Exception e) {
	         return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
	     }
	 }

	 
	 	
	 	@DeleteMapping("Equipment/{equipment_id}")
	 	public ResponseEntity<Object> deleteReserve(@PathVariable Integer equipment_id ) {
	         
	 		try {
	 			Optional<Equipment> equipment = equipmentRepository.findById(equipment_id );

	 			if (equipment.isPresent()) {
	 				equipmentRepository.delete(equipment.get());

	 				return  new ResponseEntity<>("Delete Successful", HttpStatus.OK );
	 			} else {
	 				return new ResponseEntity<>("equipment not found", HttpStatus.BAD_REQUEST);
	 			}			
	 		} catch (Exception e) {
	 			return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
	 		}
	 		
	 		
	 	}
	 	}
