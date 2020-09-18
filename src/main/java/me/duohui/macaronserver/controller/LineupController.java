package me.duohui.macaronserver.controller;

import me.duohui.macaronserver.model.Lineup;
import me.duohui.macaronserver.model.LineupMenu;
import me.duohui.macaronserver.model.Menu;
import me.duohui.macaronserver.service.LineupService;
import me.duohui.macaronserver.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RestController //
@RequestMapping("/lineup")
public class LineupController {

	@Autowired
	LineupService service;

	@Autowired
	MenuService menuService;

	@RequestMapping(value="/{lineupNumber}", method=RequestMethod.GET)
	public Lineup get(@PathVariable int lineupNumber) {
		System.out.println("lineup get 메서드 호출");
		try {
			return service.getLineup(lineupNumber);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value="/list", method=RequestMethod.GET)
	public List<LineupMenu> getMenuList(@RequestParam(value = "shopNumber") int shopNumber) {
		System.out.println("lineup menu list 메서드 호출");
		try {
			List<Lineup> list = service.getList(shopNumber);
			List<LineupMenu> lineupMenus = new ArrayList<>();

			for(Lineup lineup:list) {
				LineupMenu lineupMenu  =  new LineupMenu();
				lineupMenu.setLineupNumber(lineup.getLineupNumber());
				lineupMenu.setCol(lineup.getCol());
				lineupMenu.setRow(lineup.getRow());
				lineupMenu.setLevel(lineup.getLevel());

				Menu menu = menuService.getMenu(lineup.getMenuNumber());
				lineupMenu.setMenuName(menu.getMenuName());
				lineupMenu.setPrice(menu.getPrice());

				lineupMenu.setViewType(1);

				lineupMenus.add(lineupMenu);
			}
			return lineupMenus;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	@RequestMapping(method=RequestMethod.POST)
	public Lineup create(@RequestBody Lineup lineup) {
		System.out.println("lineup create 메서드 호출");
		try {
			return service.create(lineup);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	
	@RequestMapping(value = "/{lineupNumber}/{level}",method=RequestMethod.PUT)
	public boolean updateLevel(@PathVariable(value="lineupNumber")int lineupNumber, @PathVariable(value="level")int level) {
		System.out.println("lineup updateLevel 메서드 호출");
		try {
			if(level==0) {
				return service.updateLevelTo0(lineupNumber);
			}else if(level==1) {
				return service.updateLevelTo1(lineupNumber);
			}else if(level==2) {
				return service.updateLevelTo2(lineupNumber);
			}else return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@RequestMapping(value="/{lineupNumber}", method=RequestMethod.DELETE)
	public boolean delete(@PathVariable int lineupNumber) {
		try {
			System.out.println("lineup delete 메서드 호출");
			return service.delete(lineupNumber);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@RequestMapping(method=RequestMethod.DELETE)
	public boolean deleteAll(@RequestParam(value = "shopNumber") int shopNumber) {
		System.out.println("lineup delete all 메서드 호출");
		try {
			return service.deleteByShopNumber(shopNumber);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
