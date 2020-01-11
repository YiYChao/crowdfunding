package top.chao.funding.portal.service;

import java.util.List;
import java.util.Map;

import top.chao.funding.bean.TMember;
import top.chao.funding.vo.MemberCertVO;

/**
 * @ClassName: MemberService  
 * @Description: 会员相关的操作 接口定义
 * @author: YiYChao
 * @date 2020年1月7日 下午8:10:53
 */
public interface MemberService {

	/**
	 * @Title: queryMemerLogin
	 * @Description: 通过集合映射封装的信息查询用户是否登录
	 * @param: memberMap 会与集合信息
	 * @return: TMember 会员实体
	 * @throws: 无
	 * @date: 2020年1月7日 下午8:12:30
	 */
	TMember queryMemberLogin(Map<String, Object> memberMap);

	/**
	 * @Title: updateMemberAcctType
	 * @Description: 更新账户认证类型
	 * @param: member 账户实体
 	 * @return: int 更新的记录数
	 * @throws: 无
	 * @date: 2020年1月11日 上午9:50:36
	 */
	int updateMemberAcctType(TMember member);

	/**
	 * @Title: saveMemberCertList
	 * @Description: 保存会员资质信息
	 * @param: membercert 会员资质列表
	 * @return: void 空
	 * @throws: 无
	 * @date: 2020年1月11日 下午9:33:33
	 */
	void saveMemberCertList(List<MemberCertVO> membercert);
}
