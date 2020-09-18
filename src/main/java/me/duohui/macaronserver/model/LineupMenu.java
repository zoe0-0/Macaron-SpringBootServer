package me.duohui.macaronserver.model;

import lombok.Data;

@Data
public class LineupMenu {

    //lineup
    private int lineupNumber;
    private int row;
    private int col;
    private int level;


    //menu
    private String menuName;
    private int price;


    //
    private int viewType;
}
