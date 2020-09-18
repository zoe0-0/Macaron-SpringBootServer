package me.duohui.macaronserver.mapper;

import me.duohui.macaronserver.model.Like;
import me.duohui.macaronserver.model.Option;
import me.duohui.macaronserver.model.Shop;
import me.duohui.macaronserver.model.Showcase;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ShopMapper{
    @Select("select count(*) from shops")
    public int getCount() throws Exception;

    @Select("select * from shops where member_number=#{memberNumber}")
    public Shop selectOne(int memberNumber) throws Exception;

    @Select("select * from shops where shop_number=#{shopNumber}")
    public Shop selectOneByShopNumber(int shopNumber) throws Exception;

    @Select("select * from shops")
    public List<Shop> selectList() throws Exception;

    @Select("select * from shops where gu=#{gu}")
    public List<Shop> selectListInGu(String gu) throws Exception;

    @Select("select * from shops where gu=#{gu} and shop_name like '%${keyword}%'")
    public List<Shop> selectListSearch(Option option) throws Exception;

    @Insert("insert into shops values(null, #{shopName}, #{phoneNumber}, #{sns}, #{ownerName}, #{email}, #{memberNumber}, #{col}, #{row}, #{shopImage}, default, #{lat}, #{lon}, #{address}, #{gu})")
    public int insert(Shop shop) throws Exception;

    @Update("update shops set shop_name=#{shopName}, phone_number=#{phoneNumber}, sns=#{sns}, owner_name=#{ownerName}, email=#{email}, row=#{row}, col=#{col}, shop_image=#{shopImage}, lat=#{lat}, lon=#{lon}, address=#{address}, gu=#{gu} where shop_number=#{shopNumber}")
    public int update(Shop shop) throws Exception;

    @Update("update shops set open=1 where shop_number=#{shopNumber}")
    public int updateOpen(int shopNumber) throws Exception;

    @Update("update shops set open=0 where shop_number=#{shopNumber}")
    public int updateClose(int shopNumber) throws Exception;

    @Update("update shops set `row`=#{row}, col=#{col} where shop_number=#{shopNumber}")
    public int updateShowcase(Showcase showcase) throws Exception;

    @Select("select * from likes where customer_number=#{customerNumber} and shop_number=#{shopNumber}")
    public Like selectLike(Like like);
}
