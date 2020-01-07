package top.chao.funding.portal.service;

import java.util.Map;

import top.chao.funding.bean.TMember;

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
}
