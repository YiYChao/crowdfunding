package top.chao.funding.portal.activiti;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;

/**
 * @ClassName: AuthPassListener  
 * @Description: 会员实名认证审核通过监听器 
 * @author: YiYChao
 * @date 2020年1月12日 上午9:42:08
 */
public class AuthPassListener implements ExecutionListener{

	private static final long serialVersionUID = -7296433507605757352L;

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		System.err.println("流程审核通过！");
		
	}

}
