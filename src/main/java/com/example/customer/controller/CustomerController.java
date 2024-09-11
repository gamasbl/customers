package com.example.customer.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.customer.model.Customer;
import com.example.customer.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class CustomerController {
	@Autowired
	CustomerService customerService;

	@GetMapping("/customers")
	public Object getAllCustomer(HttpServletRequest request, HttpServletResponse response) {
		return customerService.getAllCustomer(request, response);
	}
	
	@GetMapping("/customers/{id}")
	public Object getCustomerById(@PathVariable("id") Integer id,HttpServletRequest request, HttpServletResponse response) {
		return customerService.getCustomerById(id, request, response);
	}
	
	@PostMapping("/customers")
	public Object addCustomer(@RequestBody Customer customer, HttpServletRequest request, HttpServletResponse response) {
        return customerService.addCustomer(customer, request, response);
	}
	
	@PutMapping("/customers/{id}")
	public Object editCustomer(@PathVariable Integer id, @RequestBody Customer customer,HttpServletRequest request, HttpServletResponse response) {
		return customerService.editCustomer(id, customer, request, response);
	}
	
	@DeleteMapping("/customers/{id}")
	public Object deleteCustomer(@PathVariable("id") Integer id,HttpServletRequest request, HttpServletResponse response) {
		return customerService.deleteCustomer(id, request, response);
	}
}
