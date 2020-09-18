package me.duohui.macaronserver.service;

import me.duohui.macaronserver.model.Like;
import org.springframework.stereotype.Service;

public interface LikeService {
    boolean create(Like like) throws Exception;

    boolean isSelected(Like like) throws Exception;

    boolean delete(Like like) throws Exception;
}
