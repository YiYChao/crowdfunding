package top.chao.funding.portal.activiti;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;

/**
 * @ClassName: AuthFailListener  
 * @Description: 会员实名认证审核拒绝监听器  
 * @author: YiYChao
 * @date 2020年1月12日 上午9:47:42
 */
public class AuthFailListener implements  ExecutionListener{

	private static final long serialVersionUID = -5377260581851444499L;

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		System.err.println("会员审核不通过！");
	}

}
