package com.json.startup.service.impl;

import org.springframework.stereotype.Service;

import com.json.startup.model.Customer;
import com.json.startup.service.customerService;
@Service
class customerServiceImpl implements customerService {

	
	@Override
	public boolean persistCustomer(Customer customer) {
		/*
		 Here we persist the Customer object once Json Schema is validated
		 */
		return false;
	}

}
