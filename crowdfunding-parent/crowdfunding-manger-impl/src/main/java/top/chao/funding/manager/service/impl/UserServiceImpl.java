package top.chao.funding.manager.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.chao.funding.bean.TUser;
import top.chao.funding.exception.LoginFailException;
import top.chao.funding.manager.dao.TUserMapper;
import top.chao.funding.manager.service.UserService;
import top.chao.funding.util.PageResult;
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
	public TUser queryUserLogin(Map<String, Object> userMap) {
		
		TUser user = tUserMapper.queryUserLogin(userMap);
		if(user == null) {
			throw new LoginFailException("用户名或密码不正确！");
		}
		return user;
	}

	@Override
	public PageResult<TUser> queryPage(Integer currentPage, Integer pageSizes) {
		PageResult<TUser> page = new PageResult<TUser>();
		if(currentPage <= 0) {
			currentPage = 1;
		}
		if(pageSizes <= 0) {
			pageSizes = 10;
		}
		page.setCurrentPage(currentPage);
		page.setPageSize(pageSizes);
		
		int totalRecords = tUserMapper.queryTotal();
		page.setTotalRecords(totalRecords);
		List<TUser> list = tUserMapper.queryList(page.getBegin(),page.getPageSize());
		page.setResultList(list);
		
		
		
		return page;
	}

	@Override
	public void saveUser(TUser user) {
		tUserMapper.insert(user);
	}
}
