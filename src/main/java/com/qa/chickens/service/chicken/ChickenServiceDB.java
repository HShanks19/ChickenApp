package com.qa.chickens.service.chicken;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.qa.chickens.domain.Chicken;
import com.qa.chickens.repos.ChickensRepo;


@Service
public class ChickenServiceDB implements ChickenInterface{

	private ChickensRepo repo;

    public ChickenServiceDB(ChickensRepo repo) {
        super();
        this.repo = repo;
    }
    
	@Override
	public Chicken createChicken(Chicken chicken) {
		 return this.repo.save(chicken);
	}

	@Override
	public List<Chicken> getChickens() {
		return this.repo.findAll();
	}

	@Override
	public Chicken getChickenById(long id) {
		Optional<Chicken> existingOptional = this.repo.findById(id);
		Chicken existing = existingOptional.get();
		return existing;
	}

	@Override
	public Chicken updateChicken(long id, Chicken newChicken) {
		Chicken existing = this.getChickenById(id);

		existing.setAge(newChicken.getAge());
		existing.setName(newChicken.getName());
		existing.setBreed(newChicken.getBreed());
		existing.setColour(newChicken.getColour());

		return this.repo.save(existing);
	}
	
	@Override
	public boolean removeChicken(long id) {
        this.repo.deleteById(id);
        boolean exists = this.repo.existsById(id);
        return !exists;
    }

	@Override
	public List<Chicken> getChickenByName(String name) {
		return this.repo.findByName(name);
	}

	@Override
	public List<Chicken> getChickensByBreed(String breed) {
		return this.repo.findByBreed(breed);
	}
	

	

}
