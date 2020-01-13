package top.chao.funding.manager.service;

import java.util.List;

import top.chao.funding.bean.TMember;
import top.chao.funding.bean.TTicket;
import top.chao.funding.vo.MemberCertVO;

/**
 * @ClassName: AuthCertService  
 * @Description: 会员实名认证审核相关操作接口定义 
 * @author: YiYChao
 * @date 2020年1月12日 下午2:18:14
 */
public interface AuthCertService {

	/**
	 * @Title: queryTicketByPiid
	 * @Description: 通过流程实例id查询流程审批单
	 * @param: processInstanceId 流程实例id
	 * @return: TTicket 流程审批单
	 * @throws: 无
	 * @date: 2020年1月12日 下午2:19:29
	 */
	TTicket queryTicketByPiid(String piid);

	/**
	 * @Title: queryMemberById
	 * @Description: 通过会员id查询会员实体信息
	 * @param: memberid 会员主键id
	 * @return: TMember 会员实体
	 * @throws: 无
	 * @date: 2020年1月12日 下午2:24:14
	 */
	TMember queryMemberById(int memberid);

	/**
	 * @Title: queryMemberCertByMemberid
	 * @Description: 通过会员id查询会员的资质信息
	 * @param: memberid 会员id
	 * @return: List<MemberCertVO> 会员资质列表
	 * @throws: 无
	 * @date: 2020年1月12日 下午4:13:10
	 */
	List<MemberCertVO> queryMemberCertByMemberid(Integer memberid);

}
