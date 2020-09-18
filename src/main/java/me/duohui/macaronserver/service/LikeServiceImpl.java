package me.duohui.macaronserver.service;

import me.duohui.macaronserver.mapper.LikeMapper;
import me.duohui.macaronserver.model.Like;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeServiceImpl implements LikeService{
    @Autowired
    LikeMapper likeMapper;

    @Override
    public boolean create(Like like) throws Exception {
        int result = likeMapper.insert(like);
        return result == 1;
    }

    @Override
    public boolean isSelected(Like like) throws Exception {
        Like result = likeMapper.select(like);
        if(result==null) return false; //없다
        else return true; //있다
    }

    @Override
    public boolean delete(Like like) throws Exception {
        int result = likeMapper.delete(like);
        return result == 1;
    }
}
