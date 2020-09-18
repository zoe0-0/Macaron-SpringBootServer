package me.duohui.macaronserver.controller;

import me.duohui.macaronserver.model.Customer;
import me.duohui.macaronserver.model.Menu;
import me.duohui.macaronserver.model.Shop;
import me.duohui.macaronserver.model.Showcase;
import me.duohui.macaronserver.service.CustomerService;
import me.duohui.macaronserver.service.MenuService;
import me.duohui.macaronserver.service.ShopService;
import me.duohui.macaronserver.util.DistanceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@RestController //
@RequestMapping("/menu")
public class MenuController {

	@Autowired
	MenuService service;

	@RequestMapping(value="/{menuNumber}",method=RequestMethod.GET)
	public Menu get(@PathVariable int menuNumber) {
		System.out.println("menu get 메서드 호출");
		try {
			return service.getMenu(menuNumber);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value="/list", method=RequestMethod.GET)
	public List<Menu> getList(@RequestParam(value = "shopNumber") int shopNumber) {
		System.out.println("menu get 메서드 호출");
		try {
			return service.getList(shopNumber);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(method=RequestMethod.POST)
	public Menu create(@RequestBody Menu menu) {
		System.out.println("menu create 메서드 호출");
		try {
			return service.create(menu);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(method=RequestMethod.PUT)
	public boolean update(@RequestBody Menu menu) {
		System.out.println("menu update 메서드 호출");
		try {
			return service.update(menu);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@RequestMapping(value="/{menuNumber}",method=RequestMethod.DELETE)
	public boolean delete(@PathVariable int menuNumber) {
		System.out.println("menu delete 메서드 호출");
		try {
			return service.delete(menuNumber);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	//내가 작성한 부분 - 즐겨찾기로 추가된 메뉴리스트의 재정렬을 위해 rownum 값 받아오기
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public int getIndex(@RequestParam(value = "menuNumber") int menuNumber) {
		System.out.println("get index 메서드 호출");
		try {
			return service.getIndex(menuNumber);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

}
