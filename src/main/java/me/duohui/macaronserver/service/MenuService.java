package me.duohui.macaronserver.service;

import me.duohui.macaronserver.model.Menu;

import java.util.List;

public interface MenuService {

	Menu create(Menu menu) throws Exception;

	Menu getMenu(int menuNumber) throws Exception;

	List<Menu> getList(int shopNumber) throws Exception;

	boolean update(Menu menu) throws Exception;

	boolean delete(int menuNumber) throws Exception;

	int getIndex(int menuNumber) throws Exception;
}
