package com.qa.chickens.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.chickens.domain.Chicken;
import com.qa.chickens.service.chicken.ChickenServiceList;

@RestController
public class ChickenController {
	
	private ChickenServiceList service;

	public ChickenController(ChickenServiceList service) {
		super();
		this.service = service;
	}
	
	@PostMapping("/createChicken")
	public ResponseEntity<Chicken> createChicken(@RequestBody Chicken chicken) {
		return new ResponseEntity<Chicken>(this.service.createChicken(chicken), HttpStatus.CREATED);
	}

	@GetMapping("/getChickens")
	public ResponseEntity<List<Chicken>> getChickens() {
		return ResponseEntity.ok(this.service.getChickens());
	}

	@GetMapping("/getChicken/{id}")
	public Chicken getChickenById(@PathVariable int id) {
		return this.service.getChickenById(id);
	}

	@DeleteMapping("/removeChicken/{id}")
	public Chicken removeChicken(@PathVariable int id) {
		return this.service.removeChicken(id);
	}
	
	@PutMapping("/updateChicken/{id}")
    public Chicken updateChicken(@PathVariable int id, @RequestBody Chicken chicken) {
        return this.service.updateChicken(id, chicken);
    }

}
