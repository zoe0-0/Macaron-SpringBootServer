package me.duohui.macaronserver.mapper;

import me.duohui.macaronserver.model.Like;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface LikeMapper {

    //좋아요 누름
    @Insert("insert into likes values(null, #{customerNumber}, #{shopNumber})")
    public int insert(Like like) throws Exception;

    //좋아요 취소
    @Delete("delete from likes where customer_number=#{customerNumber} and shop_number=#{shopNumber}")
    public int delete(Like like) throws Exception; //customerNumber, shopNumber만 보내주면 됨

    //좋아요눌렀는지 검사
    @Select("select * from likes where customer_number=#{customerNumber} and shop_number=#{shopNumber}")
    public Like select(Like like) throws Exception; //customerNumber, shopNumber만 보내주면 됨
}
