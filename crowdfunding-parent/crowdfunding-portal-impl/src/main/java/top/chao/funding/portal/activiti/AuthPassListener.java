package top.chao.funding.portal.activiti;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.springframework.context.ApplicationContext;

import top.chao.funding.bean.TTicket;
import top.chao.funding.portal.service.TicketService;
import top.chao.funding.util.Const;

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
		// 审批通过
		Integer memberid = (Integer)execution.getVariable("memberid");
		
		// 获取Spring容器
		ApplicationContext context = ApplicationContextUtils.applicationContext;
		
		// 获取service
		TicketService ticketService = context.getBean(TicketService.class);
		// 改变会员的状态
		ticketService.authMemberCert(memberid,Const.MEMBER_PASS);
		
		// TicketID
		TTicket ticket = ticketService.queryTicketByMemberId(memberid);
		System.out.println(ticket);
		// 改变流程审批单的状态
		ticket.setStatus("1");
		ticketService.updateTicket(ticket);

		System.err.println("流程审核通过！");
		
	}

}
