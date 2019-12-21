package top.chao.funding.manager.service;

import java.util.List;

import top.chao.funding.bean.TRole;

/**
 * @ClassName: RoleService  
 * @Description: 角色权限相关的接口定义 
 * @author: YiYChao
 * @date 2019年12月21日 下午2:18:21
 */
public interface RoleService {

	/**
	 * @Title: queryRoleAll
	 * @Description: 查询所有的角色
	 * @return: List<TRole> 角色列表
	 * @throws: 无
	 * @date: 2019年12月21日 下午2:20:33
	 */
	List<TRole> queryRoleAll();
	
	/**
	 * @Title: queryUserRoles
	 * @Description: 根据用户的主键查询用户的角色id
	 * @param: userid 用户主键
	 * @return: List<Integer> 角色主键列表
	 * @throws: 无
	 * @date: 2019年12月21日 下午2:59:34
	 */
	List<Integer> queryUserRoles(Integer userid);

	/**
	 * @Title: saveUserRole
	 * @Description: 保存用户角色
	 * @param: userid 用户主键
	 * @param: ids 角色id
	 * @return: int 影响的记录数
	 * @throws: 无
	 * @date: 2019年12月21日 下午3:50:17
	 */
	int saveUserRole(Integer userid, List<Integer> ids);

	/**
	 * @Title: deleteUserRole
	 * @Description: 删除用户角色
	 * @param: userid 用户主键
	 * @param: ids 角色id
	 * @param: @return
	 * @return: int影响的记录数
	 * @throws: 无
	 * @date: 2019年12月21日 下午3:50:55
	 */
	int deleteUserRole(Integer userid, List<Integer> ids);
}
