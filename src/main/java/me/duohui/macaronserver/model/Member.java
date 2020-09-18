package me.duohui.macaronserver.model;

import lombok.Data;

@Data
public class Member {
	private int memberNumber;
	private int type;
	private String id;
	private String password;
}
