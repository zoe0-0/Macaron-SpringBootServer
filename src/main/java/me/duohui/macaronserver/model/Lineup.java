package me.duohui.macaronserver.model;

import lombok.Data;

@Data
public class Lineup {
	private int lineupNumber;
	private int row;
	private int col;
	private int level;
	private int menuNumber;
	private int shopNumber;
}
