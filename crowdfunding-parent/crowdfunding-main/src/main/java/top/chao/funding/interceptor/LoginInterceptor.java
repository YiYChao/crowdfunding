package top.chao.funding.interceptor;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import top.chao.funding.bean.TMember;
import top.chao.funding.bean.TUser;
import top.chao.funding.util.Const;

/**
 * @ClassName: LoginInterceptor  
 * @Description: 登录的拦截器
 * @author: YiYChao
 * @date 2019年12月26日 上午10:51:08
 */
public class LoginInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request,
		HttpServletResponse response, Object handler) throws Exception {
		
		String requestURI = request.getRequestURI();
//		System.out.println("requestURI="+requestURI);
		//白名单
		Set<String> uris = new HashSet<String>();
		uris.add(Const.APP_PATH + "/doLogin.do");
		uris.add(Const.APP_PATH + "/login.html"); 	//必须增加到白名单,否则,死循环
		uris.add(Const.APP_PATH + "/index.html");
		
		if(uris.contains(requestURI)){
			return true ;
		}else{
			HttpSession session = request.getSession();
			TUser user  = (TUser)session.getAttribute(Const.LOGIN_USER);	// 从Session域中取出管理用户的信息
			TMember member = (TMember) session.getAttribute(Const.LOGIN_MEMBER);	// 从Session域中取出管理用户的信息
			if(user==null && member == null){
				response.sendRedirect(request.getContextPath()+"/login.html");
				return false ;
			}else{
				return true ;
			}
		}
	}

}
