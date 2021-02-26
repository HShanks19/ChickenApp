package com.qa.chickens.service.chicken;

import java.util.List;

import com.qa.chickens.domain.Chicken;

public interface ChickenInterface {

		Chicken createChicken(Chicken chicken);

		List<Chicken> getChickens();

		Chicken getChickenById(int id);

		Chicken removeChicken(int id);
		
		Chicken updateChicken(int id, Chicken chicken);
	

}
