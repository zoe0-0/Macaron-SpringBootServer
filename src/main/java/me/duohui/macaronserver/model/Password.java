package me.duohui.macaronserver.model;

import lombok.Data;

@Data
public class Password {
	private String id;
	private String oldPassword;
	private String newPassword;
}
