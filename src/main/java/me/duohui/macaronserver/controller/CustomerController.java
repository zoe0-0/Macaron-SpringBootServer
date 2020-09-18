package me.duohui.macaronserver.controller;

import me.duohui.macaronserver.model.Customer;
import me.duohui.macaronserver.model.Like;
import me.duohui.macaronserver.model.Location;
import me.duohui.macaronserver.service.CustomerService;
import me.duohui.macaronserver.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController //
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	CustomerService service;

	@Autowired
	LikeService likeService;

	@RequestMapping(method = RequestMethod.GET)
	public Customer get(@RequestParam(value = "memberNumber") int memberNumber) {
		System.out.println("customer get 메서드 호출");
		try {
			return service.getCustomer(memberNumber);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "/{customerNumber}", method = RequestMethod.GET)
	public Customer getByCustomerNumber(@PathVariable int customerNumber) {
		System.out.println("customer get(by customerNumber) 메서드 호출");
		try {
			return service.getCustomerByCustomerNumber(customerNumber);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public boolean create(@RequestBody Customer customer) {
		System.out.println("customer create 메서드 호출");
		try {
			return service.create(customer);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@RequestMapping(method = RequestMethod.PUT)
	public boolean update(@RequestBody Customer customer) {
		System.out.println("customer update 메서드 호출");
		try {
			return service.update(customer);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@RequestMapping(value = "/location", method = RequestMethod.PUT)
	public boolean close(@RequestBody Location location) {
		System.out.println("customer updateLocation 메서드 호출");
		try {
			return service.updateLocation(location);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@RequestMapping(value = "/{customerNumber}/like/{shopNumber}", method = RequestMethod.POST)
	public boolean like(@PathVariable(value = "customerNumber") int customerNumber, @PathVariable(value = "shopNumber") int shopNumber) {
		System.out.println("like 메서드 호출");
		try {
			Like like = new Like();
			like.setCustomerNumber(customerNumber);
			like.setShopNumber(shopNumber);
			return likeService.create(like);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@RequestMapping(value = "/{customerNumber}/like/{shopNumber}", method = RequestMethod.DELETE)
	public boolean likeCancel(@PathVariable(value = "customerNumber") int customerNumber, @PathVariable(value = "shopNumber") int shopNumber) {
		System.out.println("like cancel 메서드 호출");
		try {
			Like like = new Like();
			like.setCustomerNumber(customerNumber);
			like.setShopNumber(shopNumber);
			return likeService.delete(like);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@RequestMapping(value = "/{customerNumber}/like/{shopNumber}", method = RequestMethod.GET)
	public boolean likeCheck(@PathVariable(value = "customerNumber") int customerNumber, @PathVariable(value = "shopNumber") int shopNumber) {
		System.out.println("like check 메서드 호출");
		try {
			Like like = new Like();
			like.setCustomerNumber(customerNumber);
			like.setShopNumber(shopNumber);
			return likeService.isSelected(like);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
