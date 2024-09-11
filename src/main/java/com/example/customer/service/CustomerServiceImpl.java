package com.example.customer.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.customer.helper.MetricHelper;
import com.example.customer.model.Customer;
import com.example.customer.repository.CustomerRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	MetricHelper metricHelper;
	

    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
	
    public Object getAllCustomer(HttpServletRequest request, HttpServletResponse response) {
    	metricHelper.incrementSuccess(request.getRequestURI());
		return customerRepository.findAll();
	}

	public Object getCustomerById(Integer id, HttpServletRequest request, HttpServletResponse response) {
		long startTime = System.currentTimeMillis();
		Optional<Customer> customer = customerRepository.findById(id);
		
		if (customer.isEmpty()) {
			metricHelper.incrementFailure(request.getRequestURI());
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return "Customer not found";
		}
		metricHelper.incrementSuccess(request.getRequestURI());
		logger.info("Response: {} {} Status: {} Duration: {} ms", request.getMethod(), request.getRequestURI(), response.getStatus(), startTime - System.currentTimeMillis());
		return customer;
	}

	public Object addCustomer(Customer customer, HttpServletRequest request, HttpServletResponse response) {
		long startTime = System.currentTimeMillis();

		if (customer.nama.equalsIgnoreCase(null) || customer.dob == null || customer.gender == null || customer.ngik == null) {
			metricHelper.incrementFailure(request.getRequestURI());
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			logger.error("Response: {} {} Status: {} Duration: {} ms", request.getMethod(), request.getRequestURI(), response.getStatus(), startTime - System.currentTimeMillis());
			return "Something is null";
		}
		metricHelper.incrementSuccess(request.getRequestURI());
		response.setStatus(HttpServletResponse.SC_CREATED);
		customerRepository.save(customer);
        logger.info("Response: {} {} Status: {} Duration: {} ms", request.getMethod(), request.getRequestURI(), response.getStatus(), startTime - System.currentTimeMillis());
        return "success";
	}

	public Object editCustomer(Integer id, Customer customer, HttpServletRequest request, HttpServletResponse response) {
		long startTime = System.currentTimeMillis();
		Optional<Customer> customerById = customerRepository.findById(id);
		
		if (customerById.isEmpty()) {
			metricHelper.incrementFailure(request.getRequestURI());
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			logger.error("Response: {} {} Status: {} Duration: {} ms", request.getMethod(), request.getRequestURI(), response.getStatus(), startTime - System.currentTimeMillis());
			return "Id not found";
		}
		metricHelper.incrementSuccess(request.getRequestURI());
		customerRepository.deleteById(id);
		customerRepository.save(customer);
		logger.info("Response: {} {} Status: {} Duration: {} ms", request.getMethod(), request.getRequestURI(), response.getStatus(), startTime - System.currentTimeMillis());
		return "success";
	}

	public Object deleteCustomer(Integer id, HttpServletRequest request, HttpServletResponse response) {
		long startTime = System.currentTimeMillis();
		Optional<Customer> customer = customerRepository.findById(id);
		
		if (customer.isEmpty()) {
			metricHelper.incrementFailure(request.getRequestURI());
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			logger.error("Response: {} {} Status: {} Duration: {} ms", request.getMethod(), request.getRequestURI(), response.getStatus(), startTime - System.currentTimeMillis());
			return "Id not found";
		}
		metricHelper.incrementSuccess(request.getRequestURI());
		customerRepository.deleteById(id);
		logger.info("Response: {} {} Status: {} Duration: {} ms", request.getMethod(), request.getRequestURI(), response.getStatus(), startTime - System.currentTimeMillis());
		return "success";
	}

	
}
