package top.chao.funding.manager.cotroller;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import top.chao.funding.bean.TUser;
import top.chao.funding.manager.service.UserService;
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
	public String toUserIndex(@RequestParam(value="currentPage", required=false, defaultValue="1") Integer currentPage,
			@RequestParam(value="pageSizes", required=false, defaultValue="10") Integer pageSizes, Map map) {
		PageResult<TUser> page = userService.queryPage(currentPage,pageSizes);
		map.put("page", page);
		return "/user/user";
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public PageResult<TUser> getList(@RequestParam(value="currentPage", required=false, defaultValue="1") Integer currentPage,
			@RequestParam(value="pageSizes", required=false, defaultValue="10") Integer pageSizes, Map map) {
		PageResult<TUser> page = userService.queryPage(currentPage,pageSizes);
		map.put("page", page);
		return page;
	}
}
