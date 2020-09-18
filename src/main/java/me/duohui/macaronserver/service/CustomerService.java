package me.duohui.macaronserver.service;


import me.duohui.macaronserver.model.Customer;
import me.duohui.macaronserver.model.Location;
import org.springframework.stereotype.Service;

public interface CustomerService {
	
	boolean create(Customer customer) throws Exception;
	
	Customer getCustomer(int memberNumber) throws Exception;
	
	Customer getCustomerByCustomerNumber(int customerNumber) throws Exception;

	boolean update(Customer customer) throws Exception;

	boolean updateLocation(Location location) throws Exception;
	
}
