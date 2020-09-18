package me.duohui.macaronserver.model;

import lombok.Data;

@Data
public class Menu {
	private int menuNumber;
	private String menuName;
	private int price;
	private int bookmark; //0이면 즐겨찾기x, 1이면 즐겨찾는 메뉴
	private int shopNumber;
}
