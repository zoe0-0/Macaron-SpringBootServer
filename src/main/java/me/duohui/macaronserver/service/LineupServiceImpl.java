package me.duohui.macaronserver.service;

import me.duohui.macaronserver.mapper.LineupMapper;
import me.duohui.macaronserver.model.Lineup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LineupServiceImpl implements LineupService {

	@Autowired
	LineupMapper lineupMapper;

	@Override
	public Lineup create(Lineup lineup) throws Exception {
		int result = lineupMapper.insert(lineup);
		return lineup;
	}

	@Override
	public Lineup getLineup(int lineupNumber) throws Exception {
		return lineupMapper.selectOne(lineupNumber);
	}

	@Override
	public List<Lineup> getList(int shopNumber) throws Exception {
		return lineupMapper.selectList(shopNumber);
	}

	@Override
	public boolean updateLevelTo0(int lineupNumber) throws Exception {
		int result = lineupMapper.updateLevelTo0(lineupNumber);
		return result == 1;
	}

	@Override
	public boolean updateLevelTo1(int lineupNumber) throws Exception {
		int result = lineupMapper.updateLevelTo1(lineupNumber);
		return result == 1;
	}

	@Override
	public boolean updateLevelTo2(int lineupNumber) throws Exception {
		int result = lineupMapper.updateLevelTo2(lineupNumber);
		return result == 1;
	}

	@Override
	public boolean updateAllTo0(int shopNumber) throws Exception {
		int result = lineupMapper.updateAllTo0(shopNumber);
		return result > 0;
	}

	@Override
	public boolean updateAllTo2(int shopNumber) throws Exception {
		int result = lineupMapper.updateAllTo2(shopNumber);
		return result > 0;
	}

	@Override
	public boolean delete(int lineupNumber) throws Exception {
		int result = lineupMapper.delete(lineupNumber);
		return result == 1;
	}

	@Override
	public boolean deleteByShopNumber(int shopNumber) throws Exception {
		int result = lineupMapper.deleteByShopNumber(shopNumber);
		System.out.println("라인업 테이블에서 "+result+"개의 메뉴 삭제");
		return true;
	}

}
