package top.chao.funding.manager.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.chao.funding.bean.TUserRole;
import top.chao.funding.bean.TUserRoleExample;

public interface TUserRoleMapper {
    long countByExample(TUserRoleExample example);

    int deleteByExample(TUserRoleExample example);

    int insert(TUserRole record);

    int insertSelective(TUserRole record);

    List<TUserRole> selectByExample(TUserRoleExample example);

    int updateByExampleSelective(@Param("record") TUserRole record, @Param("example") TUserRoleExample example);

    int updateByExample(@Param("record") TUserRole record, @Param("example") TUserRoleExample example);

	 /**
	 * @Title: queryUserRoles
	 * @Description: 根据用户主键查询用户的角色id
	 * @param: userid 用户主键
	 * @return: List<Integer> 角色主键列表
	 * @throws: 无
	 * @date: 2019年12月21日 下午3:03:46
	 */
	List<Integer> queryUserRoles(Integer userid);

	/**
	 * @Title: saveUserRole
	 * @Description: 保存用户角色 
	 * @param: userid 用户id
	 * @param: ids 角色id列表
	 * @return: int 影响的记录数
	 * @throws: 无
	 * @date: 2019年12月21日 下午3:53:19
	 */
	int saveUserRole(@Param("userid")Integer userid, @Param("ids")List<Integer> ids);

	/**
	 * @Title: deleteUserRole
	 * @Description: 删除用户角色
	 * @param: userid 用户主键
	 * @param: ids 角色主键列表
	 * @return: int 影响的记录数
	 * @throws: 无
	 * @date: 2019年12月21日 下午3:54:23
	 */
	int deleteUserRole(@Param("userid")Integer userid, @Param("ids")List<Integer> ids);
}