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
	
//	@RequestMapping("/list")
//	@ResponseBody
//	public AjaxResult getList(@RequestParam(value="currentPage", required=false, defaultValue="1") Integer currentPage,
//			@RequestParam(value="pageSizes", required=false, defaultValue="10") Integer pageSizes) {
//		AjaxResult result = new AjaxResult();
//		try {
//			result.setSuccess(true);	// 设置查询状态
//			PageResult<TUser> page = userService.queryPage(currentPage,pageSizes);	// 进行查询
//			result.setPageResult(page);	// 设置查询结果
//		} catch (Exception e) {
//			result.setSuccess(false);	// 设置查询状态
//			result.setMessage("获取用户信息失败！");	// 设置失败信息
//			e.printStackTrace();
//		}
//		return result;
//	}
}
