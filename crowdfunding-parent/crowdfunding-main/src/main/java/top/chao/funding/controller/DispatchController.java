package top.chao.funding.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import top.chao.funding.bean.TUser;
import top.chao.funding.manager.service.UserService;
import top.chao.funding.util.Const;

/**
 * @ClassName: DispatchController  
 * @Description: 转发控制器 
 * @author: YiYChao
 * @date 2019年12月16日 下午4:55:57
 */
@Controller
public class DispatchController {

	@Autowired 
	private UserService userService;
	
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	@RequestMapping("/main")
	public String LoginSuccess() {
		return "main";
	}
	
	@RequestMapping("/doLogin")
	public String doLogin(String loginacct, String userpswd,String type, HttpSession session) {
		HashMap<String, Object> userMap = new HashMap<String, Object>();
		userMap.put("loginacct", loginacct);
		userMap.put("userpswd", userpswd);
		userMap.put("type", type);
		TUser user = userService.queryUserLogin(userMap);
		session.setAttribute(Const.LOGIN_USER, user);
		return "redirect:/main.html";
	}
	
}
