package top.chao.funding.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
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
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

}
