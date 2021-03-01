package com.qa.chickens.service.chicken;

import java.util.List;

import com.qa.chickens.domain.Chicken;

public interface ChickenInterface {

		Chicken createChicken(Chicken chicken);

		List<Chicken> getChickens();

		Chicken getChickenById(long id);
		
		Chicken updateChicken(long id, Chicken chicken);

		boolean removeChicken(long id);

		List<Chicken> getChickenByName(String name);
		
		List<Chicken> getChickensByBreed(String breed);
	

}
