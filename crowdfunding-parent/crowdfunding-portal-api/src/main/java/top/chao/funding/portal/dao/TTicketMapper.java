package top.chao.funding.portal.dao;

import org.apache.ibatis.annotations.Param;

import top.chao.funding.bean.TTicket;

public interface TTicketMapper {

	int insertTicket(Integer memberId);
	
	int updateTicket(TTicket ticket);
	
	TTicket selectTicketByMemberId(Integer memberId);

	/**
	 * @Title: authMemberCert
	 * @Description: 实名认证审核
	 * @param: memberid 会员id
	 * @param: authstatus 审核结果
	 * @return: int 审核的记录数
	 * @throws: 无
	 * @date: 2020年1月12日 下午5:07:57
	 */
	int authMemberCert(@Param("id")Integer memberid, @Param("auth")String auth);
}
