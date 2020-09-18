package me.duohui.macaronserver.model;

import lombok.Data;

@Data
public class Customer extends Member {
	private int customerNumber;
	private String customerName;
	private String nickname;
	private String email;
	private double lat;
	private double lon;
	private String address;
	private String gu;
}
