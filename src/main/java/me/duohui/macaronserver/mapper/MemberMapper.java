package me.duohui.macaronserver.mapper;

import me.duohui.macaronserver.model.Member;
import me.duohui.macaronserver.model.Password;
import org.apache.ibatis.annotations.*;

//Spring에서는 XML을 사용해 SQL과 객체를 매핑해야 하지만
//SpringBoot에서는 어노테이션을 사용해서 쉽게 매핑가능
@Mapper
public interface MemberMapper{
	@Select("select count(*) from members")
	public int getCount() throws Exception;
	
	@Select("select * from members where id=#{id}")
    public Member selectOne(String id) throws Exception;

    @Insert("insert into members values(null, #{type}, #{id}, #{password})")
    @Options(useGeneratedKeys = true, keyProperty = "memberNumber") //auto increment된 member_number를 받아서 shop/customer 생성해야 함 
    public int insert(Member member) throws Exception;
    
    @Update("update members set password=#{newPassword} where id=#{id} and password=#{oldPassword}")
    public int changePassword(Password password) throws Exception;
    
    @Delete("delete from members where member_number=#{memberNumber}")
    public int delete(int memberNumber) throws Exception;

}