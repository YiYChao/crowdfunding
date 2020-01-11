package top.chao.funding.portal.dao;

import top.chao.funding.bean.TTicket;

public interface TTicketMapper {

	int insertTicket(Integer memberId);
	
	int updateTicket(TTicket ticket);
	
	TTicket selectTicketByMemberId(Integer memberId);
}
