package me.duohui.macaronserver.service;

import me.duohui.macaronserver.model.Member;
import me.duohui.macaronserver.model.Password;

public interface MemberService {
	Member checkLogin(String id, String password) throws Exception;
	
	boolean create(Member member) throws Exception;
	
	Member getMember(String id) throws Exception;

	boolean changePassword(Password password) throws Exception;

	boolean delete(int memberNumber) throws Exception;
}
