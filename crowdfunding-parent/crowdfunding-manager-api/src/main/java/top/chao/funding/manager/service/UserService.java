package top.chao.funding.manager.service;

import java.util.List;
import java.util.Map;

import top.chao.funding.bean.TUser;
import top.chao.funding.util.PageResult;

/**
 * @ClassName: UserService  
 * @Description: 定义用户相关的操作的接口 
 * @author: YiYChao
 * @date 2019年12月16日 下午7:31:38
 */
public interface UserService {

	TUser queryUserLogin(Map<String, Object> userMap);

	@Deprecated
	PageResult<TUser> queryPage(Integer currentPage, Integer pageSizes);

	/**
	 * @Title: saveUser
	 * @Description: 保存用户
	 * @param: user 用户实体对象
	 * @return: int 成功记录数
	 * @throws: 无
	 * @date: 2019年12月19日 下午2:13:05
	 */
	int saveUser(TUser user);

	/**
	 * @Title: queryConditionPage
	 * @Description: 按条件分页查询用户列表信息
	 * @param: map 查询参数集合
	 * @return: PageResult<TUser> 分页对象
	 * @throws: 无
	 * @date: 2019年12月19日 下午8:18:52
	 */
	PageResult<TUser> queryConditionPage(Map<String, Object> map);

	/**
	 * @Title: queryUserByPrimaryKey
	 * @Description: 通过Id主键查询用户
	 * @param: id 主键
	 * @return: TUser 用户实体
	 * @throws: 无
	 * @date: 2019年12月20日 下午3:17:08
	 */
	TUser queryUserByPrimaryKey(Integer id);

	/**
	 * @Title: updateUserByPrimaryKey
	 * @Description: 通过主键更新用户信息
	 * @param: user 用户实体
	 * @return: int 更新记录条数
	 * @throws: 无
	 * @date: 2019年12月20日 下午3:34:17
	 */
	int updateUserByPrimaryKey(TUser user);

	/**
	 * @Title: deleteUserByPrimaryKey
	 * @Description: 根据主键删除用户
	 * @param: id 主键
	 * @return: int 删除的记录数
	 * @throws: 无
	 * @date: 2019年12月20日 下午4:10:50
	 */
	int deleteUserByPrimaryKey(Integer id);

	/**
	 * @Title: deleteUserBatchByPrimaryKey
	 * @Description: 批量删除用户
	 * @param: id 主键数组
	 * @return: int 删除的记录数
	 * @throws: 无
	 * @date: 2019年12月20日 下午7:02:57
	 */
	int deleteUserBatchByPrimaryKey(Integer[] ids);
}
