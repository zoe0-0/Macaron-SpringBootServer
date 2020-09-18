package me.duohui.macaronserver.service;

import me.duohui.macaronserver.mapper.CustomerMapper;
import me.duohui.macaronserver.mapper.MemberMapper;
import me.duohui.macaronserver.model.Customer;
import me.duohui.macaronserver.model.Location;
import me.duohui.macaronserver.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerMapper customerMapper;
	
	@Autowired
	MemberMapper memberMapper;
	
	@Transactional
	@Override
	public boolean create(Customer customer) throws Exception {
		Member member = customer;
		member.setType(1); //안드로이드에서 타입 설정 안 해도 됨
		int result = memberMapper.insert(member);
		customer.setMemberNumber(member.getMemberNumber()); //useGenerateKey 이용
		customerMapper.insert(customer);
		return result == 1;
	}

	@Override
	public Customer getCustomer(int memberNumber) throws Exception {
		return customerMapper.selectOne(memberNumber);
	}
	
	@Override
	public Customer getCustomerByCustomerNumber(int customerNumber) throws Exception {
		return customerMapper.selectOneByCustomerNumber(customerNumber);
	}

	@Transactional
	@Override
	public boolean update(Customer customer) throws Exception {
		int result = customerMapper.update(customer);
		return result == 1;
	}

	@Transactional
	@Override
	public boolean updateLocation(Location location) throws Exception {
		int result = customerMapper.updateLocation(location);
		return result == 1;
	}

}
