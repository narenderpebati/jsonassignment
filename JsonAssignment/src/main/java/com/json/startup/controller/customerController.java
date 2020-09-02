package com.json.startup.controller;

import java.io.InputStream;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.json.startup.model.Customer;
import com.json.startup.service.CustomerNotFoundException;
import com.json.startup.service.customerService;
import com.fasterxml.jackson.databind.JsonNode;
import com.networknt.schema.*;

@RestController
@RequestMapping("/api/v1")
public class customerController {

	@Autowired
	customerService service;

	private static InputStream inputStreamFromClasspath(String path) {
		return Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
	}

	@PostMapping(value = "/customer", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> readCustomer(@RequestBody Customer customer) {

		try {

			ObjectMapper objectMapper = new ObjectMapper();
			JsonSchemaFactory schemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V201909);

			try (
					InputStream schemaStream = inputStreamFromClasspath("customer.json")) {

				String customerData= objectMapper.writeValueAsString(customer);
				JsonNode json = objectMapper.readTree(customerData);
				JsonSchema schema = schemaFactory.getSchema(schemaStream);
				Set<ValidationMessage> validationResult = schema.validate(json);

				// print validation errors
				if (validationResult.isEmpty()) {
					service.persistCustomer(customer);
				} else {
					Customer cust=new Customer();
					StringBuilder errorData=new StringBuilder();
					validationResult.forEach(vm -> errorData.append(vm.getMessage().replace("$.", "")+";"));
                    cust.setError(errorData.toString());
					return new ResponseEntity<>(cust, HttpStatus.NOT_FOUND);
				}
			}

		} catch (CustomerNotFoundException ex) {
			return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
		} catch (Exception ex) {
			Customer cust = new Customer();
			return new ResponseEntity<>(cust, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(customer, HttpStatus.OK);
	}


}
