package me.duohui.macaronserver.service;

import me.duohui.macaronserver.model.Shop;
import me.duohui.macaronserver.model.Showcase;

import java.util.List;

public interface ShopService {

	boolean create(Shop shop) throws Exception;

	Shop getShop(int memberNumber) throws Exception;

	Shop getShopByShopNumber(int shopNumber) throws Exception;

	List<Shop> getList() throws Exception;

	List<Shop> getListInGu(String gu) throws Exception;

	List<Shop> getListSearch(String gu, String keyword) throws Exception;

	boolean update(Shop shop) throws Exception;

	boolean open(int shopNumber) throws Exception;

	boolean close(int shopNumber) throws Exception;

	boolean updateShowcase(Showcase showcase) throws Exception;

	boolean isBookmarked(int customerNumber, int shopNumber) throws Exception;


	
}
