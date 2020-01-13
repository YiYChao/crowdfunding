package top.chao.funding.portal.service;

import top.chao.funding.bean.TTicket;

/**
 * @ClassName: TicketService  
 * @Description: 流程审批单相关操作接口定义 
 * @author: YiYChao
 * @date 2020年1月11日 上午10:43:23
 */
public interface TicketService {

	/**
	 * @Title: queryTicketByMemberId
	 * @Description: 同辉会员Id查询流程审批单
	 * @param: memberId 会员id
	 * @return: TTicket 流程审批单实体
	 * @throws: 无
	 * @date: 2020年1月11日 上午11:32:11
	 */
	TTicket queryTicketByMemberId(Integer memberId);

	/**
	 * @Title: saveTicket
	 * @Description: 新增流程审批单
	 * @param: memberId 会员id
	 * @return: int 新增的记录数
	 * @throws: 无
	 * @date: 2020年1月11日 上午11:33:05
	 */
	int saveTicket(Integer memberId);

	/**
	 * @Title: updateTicket
	 * @Description: 更新流程审批单信息
	 * @param: ticket 流程审批单实体
	 * @return: int 更新的记录数
	 * @throws: 无
	 * @date: 2020年1月11日 下午1:42:43
	 */
	int updateTicket(TTicket ticket);

	/**
	 * @Title: authMemberCert
	 * @Description: 会员实名认证审核
	 * @param: memberid 会员主键
	 * @param: authstatus 审核状态
	 * @return: int 审核的记录数
	 * @throws: 无
	 * @date: 2020年1月12日 下午5:06:13
	 */
	int authMemberCert(Integer memberid, String authstatus);

	
	

}
