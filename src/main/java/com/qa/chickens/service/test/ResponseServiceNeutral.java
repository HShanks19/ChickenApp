package com.qa.chickens.service.test;

import org.springframework.stereotype.Service;

@Service
public class ResponseServiceNeutral implements ResponseService {

	@Override
	public String generateResponse() {
		return "Chickens!";
	}

}
