package top.chao.funding.portal.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.chao.funding.bean.TTicket;
import top.chao.funding.portal.dao.TTicketMapper;
import top.chao.funding.portal.service.TicketService;
/**
 * @ClassName: TicketServiceImpl  
 * @Description: 流程审批单相关操作接口实现 
 * @author: YiYChao
 * @date 2020年1月11日 上午10:44:17
 */
@Service
public class TicketServiceImpl implements TicketService{

	@Autowired
	private TTicketMapper tTicketMapper;
	
	@Override
	public TTicket queryTicketByMemberId(Integer memberId) {
		return tTicketMapper.selectTicketByMemberId(memberId);
	}

	@Override
	public int saveTicket(Integer memberId) {
		return tTicketMapper.insertTicket(memberId);
	}

	@Override
	public int updateTicket(TTicket ticket) {
		return tTicketMapper.updateTicket(ticket);
	}

	@Override
	public int authMemberCert(Integer memberid, String authstatus) {
		return tTicketMapper.authMemberCert(memberid,authstatus);
	}

}
