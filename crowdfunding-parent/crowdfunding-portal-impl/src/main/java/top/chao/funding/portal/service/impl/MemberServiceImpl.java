package top.chao.funding.portal.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.chao.funding.bean.TMember;
import top.chao.funding.exception.LoginFailException;
import top.chao.funding.portal.dao.TMemberMapper;
import top.chao.funding.portal.service.MemberService;
/**
 * @ClassName: MemberServiceImpl  
 * @Description: TODO 
 * @author: YiYChao
 * @date 2020年1月7日 下午8:15:20
 */
@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private TMemberMapper tMemberMapper;
	
	@Override
	public TMember queryMemberLogin(Map<String, Object> memberMap) {
		
		TMember member = tMemberMapper.queryMemberLogin(memberMap);
		if(member == null) {
			throw new LoginFailException("会员名称或密码错误！");
		}
		return member;
	}

}
