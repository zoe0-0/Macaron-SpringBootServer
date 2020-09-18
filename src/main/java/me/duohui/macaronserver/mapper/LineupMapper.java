package me.duohui.macaronserver.mapper;

import me.duohui.macaronserver.model.Lineup;
import me.duohui.macaronserver.model.Menu;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface LineupMapper{
    @Select("select count(*) from lineup")
    public int getCount() throws Exception;

    @Select("select count(*) from lineup where shop_number=#{shopNumber}")
    public int getCountInShop() throws Exception;

    @Select("select * from lineup where lineup_number=#{lineupNumber}")
    public Lineup selectOne(int lineupNumber) throws Exception;

    @Select("select * from lineup where shop_number=#{shopNumber}")
    public List<Lineup> selectList(int shopNumber) throws Exception;

    @Insert("insert into lineup values(null, #{col}, #{row}, #{level}, #{menuNumber}, #{shopNumber})")
    @Options(useGeneratedKeys = true, keyProperty = "lineupNumber")
    public int insert(Lineup lineup) throws Exception;

    @Update("update lineup set level=0 where lineup_number=#{lineUpNumber}")
    public int updateLevelTo0(int lineupNumber) throws Exception; //상태를 '많음'으로 변경

    @Update("update lineup set level=1 where lineup_number=#{lineUpNumber}")
    public int updateLevelTo1(int lineupNumber) throws Exception; //상태를 '부족'으로 변경

    @Update("update lineup set level=2 where lineup_number=#{lineUpNumber}")
    public int updateLevelTo2(int lineupNumber) throws Exception; //상태를 'soldout'으로 변경

    @Update("update lineup set level=0 where shop_number=#{shopNumber}")
    public int updateAllTo0(int shopNumber) throws Exception;

    @Update("update lineup set level=2 where shop_number=#{shopNumber}")
    public int updateAllTo2(int shopNumber) throws Exception;

    @Delete("delete from lineup where lineup_number=#{lineupNumber}")
    public int delete(int lineupNumber) throws Exception;

    @Delete("delete from lineup where shop_number=#{shopNumber}")
    public int deleteByShopNumber(int shopNumber) throws Exception;
    
}
