package com.qa.chickens.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.chickens.domain.Chicken;

@Repository
public interface ChickensRepo extends JpaRepository<Chicken,Long> {

	List<Chicken> findByName(String name);
	
	List<Chicken> findByBreed (String breed);

}
