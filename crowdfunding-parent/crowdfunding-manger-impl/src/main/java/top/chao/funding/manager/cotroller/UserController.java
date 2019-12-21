package top.chao.funding.manager.cotroller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import top.chao.funding.bean.TUser;
import top.chao.funding.manager.service.UserService;
import top.chao.funding.util.AjaxResult;
import top.chao.funding.util.PageResult;

/**
 * @ClassName: UserController  
 * @Description: 用户相关操作的前端控制器 
 * @author: YiYChao
 * @date 2019年12月18日 下午10:30:43
 */
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	
	
	@RequestMapping("/index")
	public String toUserIndex() {
		return "/user/user";
	}
	/**
	 * @Title: getList
	 * @Description: 异步请求方式进行条件查询分页查询
	 * @param: currentPage 要查询的页
	 * @param: pageSizes 每页记录数
	 * @return: AjaxResult 自定义Ajax返回实体对象
	 * @throws: 无
	 * @date: 2019年12月19日 下午8:11:22
	 */
	@RequestMapping("/list")
	@ResponseBody
	public AjaxResult getList(@RequestParam(value="currentPage", required=false, defaultValue="1") Integer currentPage,
			@RequestParam(value="pageSizes", required=false, defaultValue="10") Integer pageSizes, String condition) {
		AjaxResult result = new AjaxResult();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("currentPage", currentPage);
		map.put("pageSizes", pageSizes);
		map.put("condition", condition);
		
		try {
			result.setSuccess(true);	// 设置查询状态
			PageResult<TUser> page = userService.queryConditionPage(map);	// 进行查询
			result.setPageResult(page);	// 设置查询结果
		} catch (Exception e) {
			result.setSuccess(false);	// 设置查询状态
			result.setMessage("获取用户信息失败！");	// 设置失败信息
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping("add")
	public String toAdd() {
		return "/user/add";
	}
	/**
	 * @Title: doAdd
	 * @Description: 实现新增用户的前端控制器
	 * @return: AjaxResult 自定义Ajax实体对象
	 * @throws: 无
	 * @date: 2019年12月20日 上午11:06:46
	 */
	@RequestMapping("/doAdd")
	@ResponseBody
	public AjaxResult doAdd(String loginacct, String username, String email) {
		AjaxResult result = new AjaxResult();
		TUser user = new TUser();
		user.setLoginacct(loginacct);
		user.setUsername(username);
		user.setEmail(email);
		int res = userService.saveUser(user);
		if(res == 1) {
			result.setSuccess(true);
		}else {
			result.setSuccess(false);
			result.setMessage("新增用户失败!");
		}
		return result;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("edit")
	public String toEdit(Integer id,Map map) {
		TUser user = userService.queryUserByPrimaryKey(id);
		map.put("user", user);
		return "/user/edit";
	}
	
	@RequestMapping("/doEdit")
	@ResponseBody
	public AjaxResult doEdit(Integer id,String loginacct, String username, String email) {
		AjaxResult result = new AjaxResult();
		TUser user = new TUser();
		user.setId(id);
		user.setLoginacct(loginacct);
		user.setUsername(username);
		user.setEmail(email);
		int res = userService.updateUserByPrimaryKey(user);
		if(res == 1) {
			result.setSuccess(true);
		}else {
			result.setSuccess(false);
			result.setMessage("修改用户失败!");
		}
		return result;
	}
	
	@RequestMapping("/doDelete")
	@ResponseBody
	public AjaxResult doDelete(Integer id) {
		AjaxResult result = new AjaxResult();
		int res = userService.deleteUserByPrimaryKey(id);
		if(res == 1) {
			result.setSuccess(true);
		}else {
			result.setSuccess(false);
			result.setMessage("删除用户失败!");
		}
		return result;
	}
	@RequestMapping("/batchDelete")
	@ResponseBody
	public AjaxResult batchDelete(Integer[] ids) {
		AjaxResult result = new AjaxResult();
		int res = userService.deleteUserBatchByPrimaryKey(ids);
		if(res == ids.length) {
			result.setSuccess(true);
		}else {
			result.setSuccess(false);
			result.setMessage("删除用户失败!");
		}
		return result;
	}
}
