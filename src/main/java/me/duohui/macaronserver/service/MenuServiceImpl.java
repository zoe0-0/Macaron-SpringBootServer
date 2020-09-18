package me.duohui.macaronserver.service;

import me.duohui.macaronserver.mapper.MenuMapper;
import me.duohui.macaronserver.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
	@Autowired
	MenuMapper menuMapper;

	@Override
	public Menu create(Menu menu) throws Exception {
		int result = menuMapper.insert(menu);
		return menu;
	}

	@Override
	public Menu getMenu(int menuNumber) throws Exception {
		return menuMapper.selectOne(menuNumber);
	}

	@Override
	public List<Menu> getList(int shopNumber) throws Exception {
		return menuMapper.selectList(shopNumber);
	}

	@Override
	public boolean update(Menu menu) throws Exception {
		int result = menuMapper.update(menu);
		return result == 1;
	}

	@Override
	public boolean delete(int menuNumber) throws Exception {
		int result = menuMapper.delete(menuNumber);
		return result == 1;
	}



	//내가 작성한 부분
	@Override
	public int getIndex(int menuNumber) throws Exception {
		return  menuMapper.selectIndex(menuNumber);
	}
}
