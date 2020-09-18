package me.duohui.macaronserver.service;

import me.duohui.macaronserver.mapper.MemberMapper;
import me.duohui.macaronserver.model.Member;
import me.duohui.macaronserver.model.Password;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberMapper memberMapper;
	
	@Override
	public Member checkLogin(String id, String password) throws Exception {
		Member member = memberMapper.selectOne(id);

		if (member == null){ //해당 아이디 없음
			return null;
		}else if (!member.getPassword().equals(password)){ //비밀번호 불일치
			return null;
		}else { //로그인 성공
			return member;
		}
	}

	@Transactional // 예외 없으면 commit, 예외 있으면 rollback
	@Override
	public boolean create(Member member) throws Exception {
		int result = memberMapper.insert(member);
		return result == 1;//1을 반환하면 성공한 것 
	}

	@Override
	public Member getMember(String id) throws Exception {
		return memberMapper.selectOne(id);
	}
	
	@Transactional
	@Override
	public boolean changePassword(Password password) throws Exception {
		int result = memberMapper.changePassword(password);
		return result == 1;
	}

	@Transactional
	@Override
	public boolean delete(int memberNumber) throws Exception {
		int result = memberMapper.delete(memberNumber);
		return result == 1;
	}

}
