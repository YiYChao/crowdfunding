package top.chao.funding.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.chao.funding.bean.TRole;
import top.chao.funding.manager.dao.TRoleMapper;
import top.chao.funding.manager.dao.TUserRoleMapper;
import top.chao.funding.manager.service.RoleService;

/**
 * @ClassName: RoleServiceImpl  
 * @Description: 角色权限相关的接口实现 
 * @author: YiYChao
 * @date 2019年12月21日 下午3:01:04
 */
@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private TRoleMapper tRoleMapper;
	@Autowired
	private TUserRoleMapper tUserRoleMapper;
	
	@Override
	public List<TRole> queryRoleAll() {
		return tRoleMapper.queryRoleAll();
	}

	@Override
	public List<Integer> queryUserRoles(Integer userid) {
		return tUserRoleMapper.queryUserRoles(userid);
	}

	@Override
	public int saveUserRole(Integer userid, List<Integer> ids) {
		return tUserRoleMapper.saveUserRole(userid, ids);
	}

	@Override
	public int deleteUserRole(Integer userid, List<Integer> ids) {
		return tUserRoleMapper.deleteUserRole(userid, ids);
	}

}
