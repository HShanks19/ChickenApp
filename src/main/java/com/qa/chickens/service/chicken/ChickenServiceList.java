package com.qa.chickens.service.chicken;

import java.util.List;

import org.springframework.stereotype.Service;
import com.qa.chickens.domain.Chicken;

@Service
public class ChickenServiceList implements ChickenInterface {
	
	private List<Chicken> chickens; // <- dependency

	public ChickenServiceList(List<Chicken> chickens) {
		super();
		this.chickens = chickens;
	}

	@Override
	public Chicken createChicken(Chicken chicken) {
		this.chickens.add(chicken);
		Chicken added = this.chickens.get(this.chickens.size() - 1);
		return added;
	}

	@Override
	public List<Chicken> getChickens() {
		return this.chickens;
	}

	@Override
	public Chicken getChickenById(int id) {
		return this.chickens.get(id);
	}

	@Override
	public Chicken removeChicken(int id) {
		return this.chickens.remove(id);
	}
	
	@Override
	public Chicken updateChicken(int id, Chicken chicken) {
        // Remove existing Person with matching 'id'
        this.chickens.remove(id);
        // Add new Person in its place
        this.chickens.add(id, chicken);
        // Return updated Person from List
        return this.chickens.get(id);
    }

}
