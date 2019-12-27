package top.chao.funding.interceptor;

import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import top.chao.funding.util.Const;
/**
 * @ClassName: AuthInterceptor  
 * @Description: 定义权限拦截器 
 * @author: YiYChao
 * @date 2019年12月27日 下午7:20:46
 */
public class AuthInterceptor extends HandlerInterceptorAdapter{

	@SuppressWarnings("unchecked")
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String requestURI = request.getRequestURI();
		
		ServletContext application = request.getSession().getServletContext();
		Set<String> allPermissionUrls = (Set<String>)application.getAttribute(Const.ALL_PERMISSION_URI);

		
		if(allPermissionUrls.contains(requestURI)){			
			//获取当前用户角色的许可菜单,请求路径必须包含于许可菜单中,否则拒绝访问
			HttpSession session = request.getSession();
			
			Set<String> authUris = (Set<String>)session.getAttribute(Const.LOGIN_AUTH_PERMISSION_URI);
			
			if(authUris.contains(requestURI)){
				return true ;
			}else{
				response.sendRedirect(request.getContextPath()+"/error.html");
				return false ;
			}
		}else{ //不需要权限控制的,直接放行
			return true ;
		}
	}
}
