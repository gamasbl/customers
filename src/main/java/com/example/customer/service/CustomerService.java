package com.example.customer.service;

import com.example.customer.model.Customer;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface CustomerService {
	Object getAllCustomer(HttpServletRequest request, HttpServletResponse response);
	Object getCustomerById(Integer id,HttpServletRequest request, HttpServletResponse response);
	Object addCustomer(Customer customer, HttpServletRequest request, HttpServletResponse response);
	Object editCustomer(Integer id, Customer customer,HttpServletRequest request, HttpServletResponse response);
	Object deleteCustomer(Integer id,HttpServletRequest request, HttpServletResponse response);
}