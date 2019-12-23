package top.chao.funding.manager.cotroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import top.chao.funding.bean.TRole;
import top.chao.funding.manager.service.RoleService;
import top.chao.funding.util.AjaxResult;
import top.chao.funding.util.PageResult;
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
			// 判断角色是否拥有该角色
			if(roleids.contains(role.getId())) {
				rightRoleList.add(role);
			}else {
				leftRoleList.add(role);
			}
		}
		map.put("leftRoleList", leftRoleList);
		map.put("rightRoleList", rightRoleList);
		return "Role/assignrole";
	}
	
	
	@RequestMapping(value="/grantRole")
	@ResponseBody
	public AjaxResult grantRole(Integer Roleid, DataVO data) {
		AjaxResult result = new AjaxResult();
		int res = roleService.saveUserRole(Roleid, data.getIds());
		if(res >= 1) {
			result.setSuccess(true);
		}else {
			result.setSuccess(false);
			result.setMessage("给角色分配角色失败!");
		}
		return result;
	}
	
	
	@RequestMapping(value="/revokeRole")
	@ResponseBody
	public AjaxResult revokeRole(Integer Roleid, DataVO data) {
		AjaxResult result = new AjaxResult();
		int res = roleService.deleteUserRole(Roleid, data.getIds());
		if(res >= 1) {
			result.setSuccess(true);
		}else {
			result.setSuccess(false);
			result.setMessage("给角色取消角色失败!");
		}
		return result;
	}
	
	/**
	 * @Title: getList
	 * @Description: 异步请求方式进行条件查询分页查询
	 * @param: currentPage 要查询的页
	 * @param: pageSizes 每页记录数
	 * @return: AjaxResult 自定义Ajax返回实体对象
	 * @throws: 无
	 * @date: 2019年12月23日 下午9:20:33
	 */
	@RequestMapping("/list")
	@ResponseBody
	public AjaxResult getList(@RequestParam(value="currentPage", required=false, defaultValue="1") Integer currentPage,
			@RequestParam(value="pageSizes", required=false, defaultValue="3") Integer pageSizes, String condition) {
		AjaxResult result = new AjaxResult();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("currentPage", currentPage);
		map.put("pageSizes", pageSizes);
		map.put("condition", condition);
		
		try {
			result.setSuccess(true);	// 设置查询状态
			PageResult<TRole> page = roleService.queryConditionPage(map);	// 进行查询
			result.setPageResult(page);	// 设置查询结果
		} catch (Exception e) {
			result.setSuccess(false);	// 设置查询状态
			result.setMessage("获取角色信息失败！");	// 设置失败信息
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping("add")
	public String toAdd() {
		return "/role/add";
	}
	/**
	 * @Title: doAdd
	 * @Description: 实现新增角色的前端控制器
	 * @return: AjaxResult 自定义Ajax实体对象
	 * @throws: 无
	 * @date: 2019年12月20日 上午11:06:46
	 */
	@RequestMapping("/doAdd")
	@ResponseBody
	public AjaxResult doAdd(String roleName) {
		AjaxResult result = new AjaxResult();
		TRole role = new TRole();
		role.setName(roleName);
		int res = roleService.saveRole(role);
		if(res == 1) {
			result.setSuccess(true);
		}else {
			result.setSuccess(false);
			result.setMessage("新增角色失败!");
		}
		return result;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("edit")
	public String toEdit(Integer id,Map map) {
		TRole role = roleService.queryRoleByPrimaryKey(id);
		map.put("role", role);
		return "/role/edit";
	}
	
	@RequestMapping("/doEdit")
	@ResponseBody
	public AjaxResult doEdit(Integer id, String roleName) {
		AjaxResult result = new AjaxResult();
		TRole role = new TRole();
		role.setId(id);
		role.setName(roleName);
		int res = roleService.updateRoleByPrimaryKey(role);
		if(res == 1) {
			result.setSuccess(true);
		}else {
			result.setSuccess(false);
			result.setMessage("修改角色失败!");
		}
		return result;
	}
	
	@RequestMapping("/doDelete")
	@ResponseBody
	public AjaxResult doDelete(Integer id) {
		AjaxResult result = new AjaxResult();
		int res = roleService.deleteRoleByPrimaryKey(id);
		if(res == 1) {
			result.setSuccess(true);
		}else {
			result.setSuccess(false);
			result.setMessage("删除角色失败!");
		}
		return result;
	}
	@RequestMapping("/batchDelete")
	@ResponseBody
	public AjaxResult batchDelete(Integer[] ids) {
		AjaxResult result = new AjaxResult();
		int res = roleService.deleteRoleBatchByPrimaryKey(ids);
		if(res == ids.length) {
			result.setSuccess(true);
		}else {
			result.setSuccess(false);
			result.setMessage("删除角色失败!");
		}
		return result;
	}
	
	public String toRolePermission(Integer roleId) {
		return "/role/assignpermission";
	}
	
	
	
	
}
