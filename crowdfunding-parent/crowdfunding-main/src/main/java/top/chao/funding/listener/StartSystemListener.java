package top.chao.funding.listener;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import top.chao.funding.bean.TPermission;
import top.chao.funding.manager.service.PermissionService;
import top.chao.funding.util.Const;
import top.chao.funding.util.StringUtil;
/**
 * @ClassName: StartSystemListener  
 * @Description: 服务器启动监听器 
 * @author: YiYChao
 * @date 2019年12月16日 下午2:24:26
 */
public class StartSystemListener implements ServletContextListener{

	// 在服务器启动的时候创建Application对象需要的方法
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// 将上下文路径（pageContext.request.contextPath）放置到application域中
		ServletContext application = sce.getServletContext();
		String contextPath = application.getContextPath();
		application.setAttribute("APP_PATH", contextPath);
		
		
		//获取系统所有许可权限菜单,请求路径必须包含于许可菜单中,否则拒绝访问
		ApplicationContext ioc = WebApplicationContextUtils.getWebApplicationContext(application);	// 获取IOC容器
		PermissionService permissionService = ioc.getBean(PermissionService.class);
		
		List<TPermission> allPermission = permissionService.queryAll();
		Set<String> allPermissionUrls = new HashSet<String>();
		for (TPermission permission : allPermission) {
			if(StringUtil.isNotEmpty(permission.getUrl())){
				allPermissionUrls.add(Const.APP_PATH + permission.getUrl());
			}
		}
		application.setAttribute(Const.ALL_PERMISSION_URI, allPermissionUrls);	// 访问权限
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

}
