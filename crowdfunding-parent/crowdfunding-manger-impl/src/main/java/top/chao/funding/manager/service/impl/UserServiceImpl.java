package top.chao.funding.manager.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.chao.funding.bean.TUser;
import top.chao.funding.exception.LoginFailException;
import top.chao.funding.manager.dao.TUserMapper;
import top.chao.funding.manager.service.UserService;
import top.chao.funding.util.MD5Util;
import top.chao.funding.util.PageResult;
import top.chao.funding.util.StringUtil;
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
	@Deprecated
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
	public int saveUser(TUser user) {
		user.setUserpswd(MD5Util.digest("12345"));
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		user.setCreatetime(dateFormat.format(new Date()));
		return tUserMapper.insert(user);
	}

	@Override
	public PageResult<TUser> queryConditionPage(Map<String, Object> map) {
		PageResult<TUser> page = new PageResult<TUser>();
		Integer currentPage = (Integer) map.get("currentPage");
		Integer pageSizes = (Integer) map.get("pageSizes");
		String condition = (String) map.get("condition");
		
		page.setCurrentPage(currentPage);
		page.setPageSize(pageSizes);
		
		if(StringUtil.isEmpty(condition)) {
			condition = "";
		}else {
			condition = condition.replaceAll("%", "\\\\%");	// 防止查询条件中包含%，replaceAll为正在表达式消耗\ => \\%，Java消耗一个\=>\%
		}
		condition = "%" + condition + "%";
		
		int totalRecords = tUserMapper.queryConditionTotal(condition);
		page.setTotalRecords(totalRecords);
		List<TUser> list = tUserMapper.queryConditonList(page.getBegin(),page.getPageSize(),condition);
		page.setResultList(list);
		return page;
	}
	
	@Override
	public TUser queryUserByPrimaryKey(Integer id) {
		return tUserMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public int updateUserByPrimaryKey(TUser user) {
		return tUserMapper.updateUserByPrimaryKey(user);
	}
	@Override
	public int deleteUserByPrimaryKey(Integer id) {
		return tUserMapper.deleteUserByPrimaryKey(id);
	}
	@Override
	public int deleteUserBatchByPrimaryKey(Integer[] ids) {
		int count = 0;
		// 遍历主键数组，逐条进行删除
		for(int i = 0; i < ids.length; i++) {
			int rst = tUserMapper.deleteUserByPrimaryKey(ids[i]);
			// 删除成功，累加记录删除记录数
			if(rst == 1) {
				count++;
			}
		}
		return count;
	}
}
