package me.duohui.macaronserver.service;

import me.duohui.macaronserver.model.Lineup;
import me.duohui.macaronserver.model.Menu;
import org.springframework.stereotype.Service;

import java.util.List;

public interface LineupService {


	Lineup create(Lineup lineup) throws Exception;

	Lineup getLineup(int lineupNumber) throws Exception;

	List<Lineup> getList(int shopNumber) throws Exception;

	boolean updateLevelTo0(int lineupNumber) throws Exception;

	boolean updateLevelTo1(int lineupNumber) throws Exception;

	boolean updateLevelTo2(int lineupNumber) throws Exception;

	boolean updateAllTo0(int shopNumber) throws Exception;

	boolean updateAllTo2(int shopNumber) throws Exception;

	boolean delete(int lineupNumber) throws Exception;

	boolean deleteByShopNumber(int shopNumber) throws Exception;
	
}
