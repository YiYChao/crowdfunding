package top.chao.funding.manager.service;

import java.util.List;
import java.util.Map;

import top.chao.funding.bean.TRole;
import top.chao.funding.util.PageResult;

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

	/**
	 * @Title: queryConditionPage
	 * @Description: 条件分页查询角色信息
	 * @param: map 查询条件映射
	 * @return: PageResult<TRole> 角色列表
	 * @throws: 无
	 * @date: 2019年12月23日 下午9:27:45
	 */
	PageResult<TRole> queryConditionPage(Map<String, Object> map);

	
	/**
	 * @Title: saveRole
	 * @Description: 保存角色
	 * @param: role 角色实体
	 * @return: int 新增的记录数
	 * @throws: 无
	 * @date: 2019年12月23日 下午9:28:36
	 */
	int saveRole(TRole role);

	/**
	 * @Title: queryRoleByPrimaryKey
	 * @Description: TODO
	 * @param: @param 通过主键查询角色信息
	 * @return: TRole 角色实体
	 * @throws: 无
	 * @date: 2019年12月23日 下午9:30:32
	 */
	TRole queryRoleByPrimaryKey(Integer id);

	/**
	 * @Title: updateRoleByPrimaryKey
	 * @Description: 通过主键更新角色信息
	 * @param: role 角色实体
	 * @return: int 更新的记录数
	 * @throws: 无
	 * @date: 2019年12月23日 下午9:32:23
	 */
	int updateRoleByPrimaryKey(TRole role);

	/**
	 * @Title: deleteRoleBatchByPrimaryKey
	 * @Description: 通过主键批量删除角色信息
	 * @param: ids 主键数组
	 * @return: int 删除的记录数
	 * @throws: 无
	 * @date: 2019年12月23日 下午9:33:30
	 */
	int deleteRoleBatchByPrimaryKey(Integer[] ids);

	/**
	 * @Title: deleteRoleByPrimaryKey
	 * @Description: 通过主键删除单条记录
	 * @param: id 主键
	 * @return: int 删除的记录数
	 * @throws: 无
	 * @date: 2019年12月23日 下午9:34:25
	 */
	int deleteRoleByPrimaryKey(Integer id);
}
