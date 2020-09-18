package me.duohui.macaronserver.controller;

import me.duohui.macaronserver.model.Member;
import me.duohui.macaronserver.model.Password;
import me.duohui.macaronserver.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController //
@RequestMapping("/member")
public class MemberController {

	@Autowired
	MemberService service;
	
	@RequestMapping(method=RequestMethod.POST)
	public Member get(@RequestBody Member member) {
		System.out.println("member get 메서드 호출");
		try {
			return service.checkLogin(member.getId(), member.getPassword());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public boolean changePassword(@RequestBody Password password) {
		System.out.println("member changePassword 메서드 호출");
		try {
			return service.changePassword(password);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@RequestMapping(value="/{memberNumber}", method=RequestMethod.DELETE)
	public boolean delete(@PathVariable int memberNumber) {
		System.out.println("member delete 메서드 호출");
		try {
			return service.delete(memberNumber);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
