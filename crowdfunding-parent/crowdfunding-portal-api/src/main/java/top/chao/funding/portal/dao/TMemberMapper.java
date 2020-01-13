package top.chao.funding.portal.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import top.chao.funding.bean.TMember;
import top.chao.funding.bean.TMemberExample;
import top.chao.funding.bean.TTicket;
import top.chao.funding.vo.MemberCertVO;

public interface TMemberMapper {
    long countByExample(TMemberExample example);

    int deleteByExample(TMemberExample example);

    int insert(TMember record);

    int insertSelective(TMember record);

    List<TMember> selectByExample(TMemberExample example);

    int updateByExampleSelective(@Param("record") TMember record, @Param("example") TMemberExample example);

    int updateByExample(@Param("record") TMember record, @Param("example") TMemberExample example);

    /**
     * @Title: queryMemberLogin
     * @Description: 查询会员是否登录
     * @param: memberMap 会员信息映射
     * @return: TMember 会员实体
     * @throws: 无
     * @date: 2020年1月7日 下午8:41:10
     */
	TMember queryMemberLogin(Map<String, Object> memberMap);

	/**
	 * @Title: queryTicketByPiid
	 * @Description: 通过流程实例id查询流程审批单
	 * @param: piid 流程实例主键id
	 * @return: TTicket 流程审批单
	 * @throws: 无
	 * @date: 2020年1月12日 下午3:03:41
	 */
	TTicket queryTicketByPiid(String piid);

	/**
	 * @Title: queryMemberById
	 * @Description: 通过会员id拆查询会员实体信息
	 * @param: memberid 会员id
	 * @return: TMember 会员实体
	 * @throws: 无
	 * @date: 2020年1月12日 下午3:03:46
	 */
	TMember queryMemberById(int memberid);

	/**
	 * @Title: queryMemberCertByMemberid
	 * @Description: 通过会员信息id查询会员资质信息
	 * @param: memberid 会员
	 * @return: List<TMemberCert> 会员资质信息列表
	 * @throws: 无
	 * @date: 2020年1月12日 下午4:16:05
	 */
	List<MemberCertVO> queryMemberCertByMemberid(Integer memberid);
	
}