package me.duohui.macaronserver.mapper;

import me.duohui.macaronserver.model.Customer;
import me.duohui.macaronserver.model.Location;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface CustomerMapper{
	@Select("select count(*) from customers")
	public int getCount() throws Exception;
	
	@Select("select * from customers where member_number=#{memberNumber}")
    public Customer selectOne(int memberNumber) throws Exception;
	
	@Select("select * from customers where customer_number=#{customerNumber}")
	public Customer selectOneByCustomerNumber(int customerNumber) throws Exception;

    @Insert("insert into customers values(null, #{customerName}, #{nickname}, #{email}, #{memberNumber}, #{lat}, #{lon}, #{address}, #{gu})")
    public int insert(Customer customer) throws Exception;
    
    @Update("update customers set customer_name=#{customerName}, nickname=#{nickname}, email=#{email}, lat=#{lat}, lon=#{lon}, address=#{address}, gu=#{gu} where customer_number=#{customerNumber}")
    public int update(Customer customer) throws Exception;
    
    @Update("update customers set lat=#{lat}, lon=#{lon}, address=#{address}, gu=#{gu} where customer_number=#{customerNumber}")
    public int updateLocation(Location location) throws Exception;
}
