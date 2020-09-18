package me.duohui.macaronserver.service;

import me.duohui.macaronserver.mapper.MemberMapper;
import me.duohui.macaronserver.mapper.ShopMapper;
import me.duohui.macaronserver.model.*;
import me.duohui.macaronserver.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {
	@Autowired
	ShopMapper shopMapper;

	@Autowired
	MemberMapper memberMapper;

	@Transactional
	@Override
	public boolean create(Shop shop) throws Exception {
		Member member = shop;
		member.setType(0); //안드로이드에서 타입 설정 안 해도 됨
		int result = memberMapper.insert(member);
		shop.setMemberNumber(member.getMemberNumber()); //useGenerateKey 이용
		shopMapper.insert(shop);
		return result == 1;
	}

	@Override
	public Shop getShop(int memberNumber) throws Exception {
		return shopMapper.selectOne(memberNumber);
	}

	@Override
	public Shop getShopByShopNumber(int shopNumber) throws Exception {
		return shopMapper.selectOneByShopNumber(shopNumber);
	}

	@Override
	public List<Shop> getList() throws Exception {
		return shopMapper.selectList();
	}


	//특정 구에 있는 가게 리스트 리턴
	@Override
	public List<Shop> getListInGu(String gu) throws Exception {
		return shopMapper.selectListInGu(gu);
	}


	//특정 구 내에서 특정 키워드가 들어가는 가게 리스트 리턴
	@Override
	public List<Shop> getListSearch(String gu, String keyword) throws Exception {
		Option option = new Option();
		option.setGu(gu);
		option.setKeyword(keyword);
		return shopMapper.selectListSearch(option);
	}


	@Transactional
	@Override
	public boolean update(Shop shop) throws Exception {
		if(shop.getShopImage()!=null) { //이미지도 변경
			shop.setShopImage(ImageUtil.makeThumb(shop.getShopImage())); //이미지 크기 조정
		}
		int result = shopMapper.update(shop);
		return result == 1;
	}


	@Transactional
	@Override
	public boolean open(int shopNumber) throws Exception {
		int result = shopMapper.updateOpen(shopNumber);
		return result == 1; //한 개만 업데이트되면 성공
	}

	@Transactional
	@Override
	public boolean close(int shopNumber) throws Exception {
		int result = shopMapper.updateClose(shopNumber);
		return result == 1; //한 개만 업데이트되면 성공
	}

	@Transactional
	@Override
	public boolean updateShowcase(Showcase showcase) throws Exception {
		int result = shopMapper.updateShowcase(showcase);
		return result == 1;
	}



	//좋아요 표시한 가게인지 아닌지 리턴
	@Override
	public boolean isBookmarked(int customerNumber, int shopNumber) throws Exception {
		Like like = new Like();
		like.setCustomerNumber(customerNumber);
		like.setShopNumber(shopNumber);
		Like result = shopMapper.selectLike(like);
		if(result==null) return false;
		else return true;
	}


}
