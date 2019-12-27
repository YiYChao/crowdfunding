package top.chao.funding.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import top.chao.funding.bean.TPermission;
import top.chao.funding.bean.TUser;
import top.chao.funding.manager.service.PermissionService;
import top.chao.funding.manager.service.UserService;
import top.chao.funding.util.AjaxResult;
import top.chao.funding.util.Const;
import top.chao.funding.util.MD5Util;
import top.chao.funding.util.StringUtil;

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
	@Autowired
	private PermissionService permissionService;
	
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
			
			List<TPermission> loginPermissions = permissionService.queryPermissionsByUserid(user.getId());
			
			Set<String> authUris = new HashSet<String>();
			TPermission rootPermission = null;
			Map<Integer,TPermission> map = new HashMap<Integer,TPermission>();
			for (TPermission permission : loginPermissions) {
				map.put(permission.getId(), permission);
				// 将该用户的权限存入集合
				if(StringUtil.isNotEmpty(permission.getUrl())){
					authUris.add(Const.APP_PATH + permission.getUrl());
				}
			}
			for (TPermission permission : loginPermissions) {
				if(permission.getPid()==0){
					rootPermission = permission ;
				}else{
					TPermission parent = map.get(permission.getPid());	
					parent.setOpen(true);
					parent.getChildren().add(permission);
				}
			}
			session.setAttribute(Const.LOGIN_ROOT_PERMISSION, rootPermission);	// 菜单权限
			session.setAttribute(Const.LOGIN_AUTH_PERMISSION_URI, authUris);	// 访问权限
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
