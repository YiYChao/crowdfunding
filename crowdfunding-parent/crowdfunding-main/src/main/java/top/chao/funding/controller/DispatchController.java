package top.chao.funding.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import top.chao.funding.bean.TMember;
import top.chao.funding.bean.TPermission;
import top.chao.funding.bean.TUser;
import top.chao.funding.manager.service.PermissionService;
import top.chao.funding.manager.service.UserService;
import top.chao.funding.portal.service.MemberService;
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
	@Autowired
	private MemberService memberService;
	
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/login")
	public String login(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		boolean flag = false;	// 是否需要登录的标记
		String target = "";		// 目标地址
		Cookie[] cookies = request.getCookies();	// 获取Cookie
		// 遍历Cookie
		for (Cookie ck : cookies) {
			// 找打目标Cookie
			if("rember".equals(ck.getName())) {
				// 解析Cookie并封装
				String[] rst = ck.getValue().split("#");
				
				String pwd = rst[1].substring(8).substring(0, rst[1].length() -16);	// 解析出密码
				
				AjaxResult result = doLogin(rst[0], pwd, rst[2], "once", session, response);
				flag = result.isSuccess();	// 标识是否登录成功
				if("member".equals(result.getObj())) {
					target = "redirect:/member/index.html";
				}else {
					target = "redirect:/main.html";
				}
			}
		}
		if(flag) {
			return target;
		}else {
			return "login";
		}
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
	public AjaxResult doLogin(String loginacct, String userpswd, String type, String remberme, HttpSession session,HttpServletResponse response) {
		AjaxResult result = new AjaxResult();
		try {
			Map<String, Object> userMap = new HashMap<String, Object>();
			userMap.put("loginacct", loginacct);
			userMap.put("userpswd", MD5Util.digest(userpswd));	// 采用MD5对密码进行加密
			userMap.put("type", type);
			
			if("member".equals(type)) {		// 会员用户登录
				TMember member = memberService.queryMemberLogin(userMap);
				session.setAttribute(Const.LOGIN_MEMBER, member);
				result.setSuccess(true);
				
				// 通过Cookie实现记住我两周的功能
				if("two".equals(remberme)) {
					// 由于MD5的不可解密性，以及Cookie的不安全性，在密码前后加入8个干扰字符
					String strMember = loginacct + "#" + MD5Util.digest(loginacct).substring(8, 16) + 
							userpswd + MD5Util.digest(loginacct).substring(24,32) +"#" + type;
					
					Cookie ck = new Cookie("rember", strMember);
					ck.setMaxAge(14*24*60); 	// 设置Cookie过期时间为两周
					ck.setPath("/"); 	// 设置cookie的访问路径，任何请求都可以 
					response.addCookie(ck);	// 加入相应域
				}
			}else if("user".equals(type)) {		// 管理用户登录
				TUser user = userService.queryUserLogin(userMap);
				session.setAttribute(Const.LOGIN_USER, user);
				result.setSuccess(true);
				
				// 通过Cookie实现记住我两周的功能
				if("two".equals(remberme)) {
					// 由于MD5的不可解密性，以及Cookie的不安全性，在密码前后加入8个干扰字符
					String strUser = loginacct + "#" + MD5Util.digest(loginacct).substring(8, 16) + 
							userpswd + MD5Util.digest(loginacct).substring(16,24) +"#" + type;
					Cookie ck = new Cookie("rember", strUser);
					ck.setMaxAge(14*24*60); 	// 设置Cookie过期时间为两周
					ck.setPath("/"); 	// 设置cookie的访问路径，任何请求都可以 
					response.addCookie(ck);	// 加入相应域
				}
				
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
			}else {
				result.setSuccess(false);
				result.setMessage("用户类型不正确！");
			}
			result.setObj(type); 	// 将用户类型返回给前端
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("登录失败！");
		}
		return result;
	}
}
