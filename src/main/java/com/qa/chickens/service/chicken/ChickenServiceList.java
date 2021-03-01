package com.qa.chickens.service.chicken;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qa.chickens.domain.Chicken;


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
	public Chicken getChickenById(long id) {
		return this.chickens.get(id);
	}

	@Override
	public boolean removeChicken(long id) {
		return this.chickens.remove(id);
		boolean exists = this.chickens.get(id);
        return !exists;
	}
	
	@Override
	public Chicken updateChicken(long id, Chicken chicken) {
        this.chickens.remove(id);
        this.chickens.add(id, chicken);
        return this.chickens.get(id);
    }

}
