package com.getir.readingisgood.domain.customer.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.getir.readingisgood.common.SoftwareComponent;
import com.getir.readingisgood.domain.customer.api.CustomerApi;
import com.getir.readingisgood.domain.customer.business.CustomerService;
import com.getir.readingisgood.domain.customer.model.CustomerDto;
import com.getir.readingisgood.domain.customer.model.request.CreateCustomerRequest;
import com.getir.readingisgood.domain.order.model.OrderDto;

@RestController
@RequestMapping(value = "/customer", consumes = { APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE }, produces = {
		APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE })
@SoftwareComponent(name = "CustomerController", description = "Exposes end points to customer process.", technologies = {
		"java", "rest", "https" })
public class CustomerController implements CustomerApi {

	private CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@PostMapping("/create")
	public ResponseEntity<CustomerDto> createCustomer(@RequestBody CreateCustomerRequest createReq) {
		return new ResponseEntity<>(customerService.createCustomer(createReq), HttpStatus.CREATED);
	}

	@GetMapping("/all")
	public ResponseEntity<List<CustomerDto>> getCustomers(@RequestParam(value = "PAGE", defaultValue = "0") int page,
			@RequestParam(value = "SIZE", defaultValue = "10") int size) {
		return new ResponseEntity<>(customerService.getCustomers(page, size), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CustomerDto> getCustomerById(@PathVariable("id") Long customerId) {
		return new ResponseEntity<>(customerService.getCustomerById(customerId), HttpStatus.OK);
	}
	
	@GetMapping("/{id}/orders")
	public ResponseEntity<List<OrderDto>> getAllOrders(@PathVariable("id") Long customerId) {
		return new ResponseEntity<>(customerService.getAllOrders(customerId), HttpStatus.OK);
	}


}
