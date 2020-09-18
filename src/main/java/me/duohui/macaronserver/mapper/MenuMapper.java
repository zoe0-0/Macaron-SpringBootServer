package me.duohui.macaronserver.mapper;

import me.duohui.macaronserver.model.Menu;
import org.apache.ibatis.annotations.*;
import org.springframework.core.annotation.Order;

import java.util.List;

@Mapper
public interface MenuMapper{

    @Select("select count(*) from menu")
    public int getCount() throws Exception;

    @Select("select count(*) from menu where shop_number=#{shopNumber}")
    public int getCountInShop() throws Exception;

    @Select("select * from menu where menu_number=#{menuNumber}")
    public Menu selectOne(int menuNumber) throws Exception;

    @Select("select * from menu where shop_number=#{shopNumber} order by bookmark desc, menu_number asc")
    public List<Menu> selectList(int shopNumber) throws Exception;

    @Insert("insert into menu values(null, #{menuName}, #{price}, default, #{shopNumber})")
    @Options(useGeneratedKeys = true, keyProperty = "menuNumber")
    public int insert(Menu menu) throws Exception;

    @Update("update menu set menu_name=#{menuName}, price=#{price}, bookmark=#{bookmark} where menu_number=#{menuNumber}")
    public int update(Menu menu) throws Exception;

    @Delete("delete from menu where menu_number=#{menuNumber}")
    public int delete(int menuNumber) throws Exception;


    //내가 작성한 부분
    // - 북마크 업데이트 시 현재 클릭한 아이템의 바뀐 위치를 추적해서 그 위치에 포커스를 유지하기 위해서 인덱스를 받아온다
    @Select("SELECT ROWNUM FROM  ( SELECT @ROWNUM := @ROWNUM + 1 AS ROWNUM, MENU_NUMBER FROM Menu, (SELECT @ROWNUM := 0) R Order by bookmark desc, menu_number asc)T WHERE MENU_NUMBER = #{menu_number}")
    public int selectIndex(int menuNumber) throws Exception;

}
