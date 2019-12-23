package top.chao.funding.manager.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.chao.funding.bean.TRole;
import top.chao.funding.manager.dao.TRoleMapper;
import top.chao.funding.manager.dao.TUserRoleMapper;
import top.chao.funding.manager.service.RoleService;
import top.chao.funding.util.PageResult;
import top.chao.funding.util.StringUtil;

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

	@Override
	public PageResult<TRole> queryConditionPage(Map<String, Object> map) {
		PageResult<TRole> page = new PageResult<TRole>();
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
		
		int totalRecords = tRoleMapper.queryConditionTotal(condition);
		page.setTotalRecords(totalRecords);
		List<TRole> list = tRoleMapper.queryConditonList(page.getBegin(),page.getPageSize(),condition);
		page.setResultList(list);
		return page;
	}

	@Override
	public int saveRole(TRole role) {
		return tRoleMapper.insert(role);
	}

	@Override
	public TRole queryRoleByPrimaryKey(Integer id) {
		return tRoleMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateRoleByPrimaryKey(TRole role) {
		return tRoleMapper.updateRoleByPrimaryKey(role);
	}

	@Override
	public int deleteRoleBatchByPrimaryKey(Integer[] ids) {
		int count = 0;
		// 遍历主键数组，逐条进行删除
		for(int i = 0; i < ids.length; i++) {
			int rst = tRoleMapper.deleteRoleByPrimaryKey(ids[i]);
			// 删除成功，累加记录删除记录数
			if(rst == 1) {
				count++;
			}
		}
		return count;
	}

	@Override
	public int deleteRoleByPrimaryKey(Integer id) {
		return tRoleMapper.deleteRoleByPrimaryKey(id);
	}

}
