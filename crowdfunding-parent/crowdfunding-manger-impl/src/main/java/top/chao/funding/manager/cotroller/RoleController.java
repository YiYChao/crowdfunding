package top.chao.funding.manager.cotroller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import top.chao.funding.bean.TRole;
import top.chao.funding.manager.service.RoleService;
import top.chao.funding.util.AjaxResult;
import top.chao.funding.vo.DataVO;
/**
 * @ClassName: RoleController  
 * @Description: 角色相关的前端控制器 
 * @author: YiYChao
 * @date 2019年12月21日 下午4:19:27
 */
@Controller
@RequestMapping(value="/role")
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	@RequestMapping(value="/index")
	public String toRole() {
		return "/role/role";
	}
	
	@RequestMapping(value="/assignRole")
	public String assignRole(Integer id,Map<String, List<TRole>> map) {
		List<TRole> leftRoleList = new ArrayList<TRole>();
		List<TRole> rightRoleList = new ArrayList<TRole>();
		
		List<TRole> roleAll = roleService.queryRoleAll();
		List<Integer> roleids = roleService.queryUserRoles(id);
		
		// 遍历所有的角色
		for(TRole role : roleAll) {
			// 判断用户是否拥有改角色
			if(roleids.contains(role.getId())) {
				rightRoleList.add(role);
			}else {
				leftRoleList.add(role);
			}
		}
		map.put("leftRoleList", leftRoleList);
		map.put("rightRoleList", rightRoleList);
		return "user/assignrole";
	}
	
	
	@RequestMapping(value="/grantRole")
	@ResponseBody
	public AjaxResult grantRole(Integer userid, DataVO data) {
		AjaxResult result = new AjaxResult();
		int res = roleService.saveUserRole(userid, data.getIds());
		if(res >= 1) {
			result.setSuccess(true);
		}else {
			result.setSuccess(false);
			result.setMessage("给用户分配角色失败!");
		}
		return result;
	}
	
	
	@RequestMapping(value="/revokeRole")
	@ResponseBody
	public AjaxResult revokeRole(Integer userid, DataVO data) {
		AjaxResult result = new AjaxResult();
		int res = roleService.deleteUserRole(userid, data.getIds());
		if(res >= 1) {
			result.setSuccess(true);
		}else {
			result.setSuccess(false);
			result.setMessage("给用户取消角色失败!");
		}
		return result;
	}
}
