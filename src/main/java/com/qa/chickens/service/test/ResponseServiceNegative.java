package com.qa.chickens.service.test;

public class ResponseServiceNegative implements ResponseService {

	@Override
	public String generateResponse() {
		return "Well there are some Chickens";
	}

}
