package top.chao.funding.manager.cotroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import top.chao.funding.bean.TPermission;
import top.chao.funding.manager.service.PermissionService;
import top.chao.funding.util.AjaxResult;

/**
 * @ClassName: PermissionController  
 * @Description: 许可维护前端控制器 
 * @author: YiYChao
 * @date 2019年12月22日 上午8:16:42
 */
@Controller
@RequestMapping(value="/permission")
public class PermissionController {

	@Autowired
	private PermissionService permissionService;
	
	@RequestMapping(value="/index")
	public String toIndex() {
		return "permission/permission";
	}
	
	@RequestMapping(value="/queryAll")
	@ResponseBody
	public TPermission queryAllPermission(Integer roleId) {
		AjaxResult result = new AjaxResult();
		try {
			List<TPermission> list = permissionService.queryAll();
			
			// 角色所拥有的许可id列表
			List<Integer> pids = new ArrayList<Integer>();
			if(roleId != null) {
				pids = permissionService.queryPermissionIdsByRoleId(roleId);
			}
			
			Map<Integer,TPermission> map = new HashMap<Integer,TPermission>();
			
			for(TPermission permission : list) {
				map.put(permission.getId(), permission);
				// 查询角色是否拥有该许可
				if(pids.contains(permission.getId())) {
					permission.setChecked(true);
				}
			}
			for (TPermission permission : list) {
				//假设为子菜单
				if(permission.getPid() == 0 ){
					result.setObj(permission); //根节点
				}else{
					TPermission parent = map.get(permission.getPid());	//父节点
					parent.getChildren().add(permission);	
				}
			}
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
			e.printStackTrace();
		}
		
		return (TPermission) result.getObj();
	}
	
	@RequestMapping(value="/add")
	public String toAdd() {
		return "/permission/add";
	}
	
	@RequestMapping(value="/doAdd")
	@ResponseBody
	public AjaxResult doAdd(TPermission permission) {
		AjaxResult result = new AjaxResult();
		try {
			int res= permissionService.savePermission(permission);
			result.setSuccess(res == 1);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMessage("添加许可失败！");
		}
		return result;
	}
	
	@RequestMapping(value="/edit")
	public String toEdit(Integer id, Map<String,TPermission> map) {
		TPermission permission = permissionService.queryPermissionById(id);
		map.put("permission", permission);
		return "/permission/edit";
	}
	
	@RequestMapping(value="/doEdit")
	@ResponseBody
	public AjaxResult doEdit(TPermission permission) {
		AjaxResult result = new AjaxResult();
		try {
			int res= permissionService.updatePermission(permission);
			result.setSuccess(res == 1);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMessage("修改许可失败！");
		}
		return result;
	}
	
	@RequestMapping(value="/doDelete")
	@ResponseBody
	public AjaxResult doDelete(Integer id) {
		AjaxResult result = new AjaxResult();
		try {
			int res= permissionService.deleteByPrimaryKey(id);
			result.setSuccess(res == 1);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMessage("删除许可失败！");
		}
		return result;
	}
}
