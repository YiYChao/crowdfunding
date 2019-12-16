package top.chao.funding.manager.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.chao.funding.bean.TUser;
import top.chao.funding.exception.LoginFailException;
import top.chao.funding.manager.dao.TUserMapper;
import top.chao.funding.manager.service.UserService;
/**
 * @ClassName: UserServiceImpl  
 * @Description: 实现用户相关的操作的接口 
 * @author: YiYChao
 * @date 2019年12月16日 下午7:32:53
 */
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private TUserMapper tUserMapper;

	@Override
	public TUser queryUserLogin(HashMap<String, Object> userMap) {
		
		TUser user = tUserMapper.queryUserLogin(userMap);
		System.out.println(user);
		if(user == null) {
			throw new LoginFailException("用户名或密码不正确！");
		}
		return user;
	}
}
