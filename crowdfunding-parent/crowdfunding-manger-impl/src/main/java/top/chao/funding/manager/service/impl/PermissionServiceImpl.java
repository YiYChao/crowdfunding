package top.chao.funding.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.chao.funding.bean.TPermission;
import top.chao.funding.manager.dao.TPermissionMapper;
import top.chao.funding.manager.service.PermissionService;
import top.chao.funding.util.StringUtil;

/**
 * @ClassName: PermissionServiceImpl  
 * @Description: 许可维护相关接口实现 
 * @author: YiYChao
 * @date 2019年12月22日 上午8:48:49
 */
@Service
public class PermissionServiceImpl implements PermissionService{

	@Autowired
	private TPermissionMapper tPermissionMapper;
	
	@Override
	public List<TPermission> queryAll() {
		return tPermissionMapper.selectAll();
	}

	@Override
	public int savePermission(TPermission permission) {
		// 设置许可的图标
		if(StringUtil.isEmpty(permission.getIcon())) {
			permission.setIcon("glyphicon glyphicon-envelope");
		}
		return tPermissionMapper.insert(permission);
	}

	@Override
	public int updatePermission(TPermission permission) {
		return tPermissionMapper.updatePermission(permission);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return tPermissionMapper.deleteByPrimaryKey(id);
	}

	@Override
	public TPermission queryPermissionById(Integer id) {
		return tPermissionMapper.queryPermissionById(id);
	}

	@Override
	public List<Integer> queryPermissionIdsByRoleId(Integer roleId) {
		return tPermissionMapper.queryPermissionIdsByRoleId(roleId);
	}

	@Override
	public void saveRolePermissions(Integer roleId, List<Integer> newPids) {
		if(newPids.size() == 0) {
			return;
		}else {
			tPermissionMapper.saveRolePermissions(roleId, newPids);
		}
		
	}

	@Override
	public void deleteRolePermissions(Integer roleId, List<Integer> oldPids) {
		if(oldPids.size() == 0) {
			return;
		}else {
			tPermissionMapper.deleteRolePermissions(roleId, oldPids);
		}
		
	}
}
