package top.chao.funding.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import top.chao.funding.bean.TUser;
import top.chao.funding.manager.service.UserService;
import top.chao.funding.util.AjaxResult;
import top.chao.funding.util.Const;
import top.chao.funding.util.MD5Util;

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
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
//		session.removeAttribute(Const.LOGIN_USER);
		session.invalidate(); // 销毁或者清理session域
		return "redirect:/index.html";
	}
	
	
	@RequestMapping("/doLogin")
	@ResponseBody
	public AjaxResult doLogin(String loginacct, String userpswd,String type, HttpSession session) {
		AjaxResult result = new AjaxResult();
		try {
			Map<String, Object> userMap = new HashMap<String, Object>();
			userMap.put("loginacct", loginacct);
			userMap.put("userpswd", MD5Util.digest(userpswd));	// 采用MD5对密码进行加密
			userMap.put("type", type);
			TUser user = userService.queryUserLogin(userMap);
			session.setAttribute(Const.LOGIN_USER, user);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("登录失败！");
		}
		return result;
	}
	
	/*
	 * @RequestMapping("/doLogin") public String doLogin(String loginacct, String
	 * userpswd,String type, HttpSession session) { HashMap<String, Object> userMap
	 * = new HashMap<String, Object>(); userMap.put("loginacct", loginacct);
	 * userMap.put("userpswd", userpswd); userMap.put("type", type); TUser user =
	 * userService.queryUserLogin(userMap); session.setAttribute(Const.LOGIN_USER,
	 * user); return "redirect:/main.html"; }
	 */
	
}
