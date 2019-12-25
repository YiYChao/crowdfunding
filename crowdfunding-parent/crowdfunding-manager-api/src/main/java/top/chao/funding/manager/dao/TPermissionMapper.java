package top.chao.funding.manager.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.chao.funding.bean.TPermission;
import top.chao.funding.bean.TPermissionExample;

public interface TPermissionMapper {
    long countByExample(TPermissionExample example);

    int deleteByExample(TPermissionExample example);

    int insert(TPermission record);

    int insertSelective(TPermission record);

    List<TPermission> selectByExample(TPermissionExample example);

    int updateByExampleSelective(@Param("record") TPermission record, @Param("example") TPermissionExample example);

    int updateByExample(@Param("record") TPermission record, @Param("example") TPermissionExample example);

    /**
     * @Title: selectAll
     * @Description: 查找所有的许可记录
     * @return: List<TPermission> 返回许可记录列表
     * @throws: 无
     * @date: 2019年12月22日 上午9:05:11
     */
	List<TPermission> selectAll();

	/**
	 * @Title: updatePermission
	 * @Description: 修改许可
	 * @param: permission 许可实体
	 * @return: int 影响的记录数
	 * @throws: 无
	 * @date: 2019年12月22日 下午2:07:59
	 */
	int updatePermission(TPermission permission);

	/**
	 * @Title: deleteByPrimaryKey
	 * @Description: 根据主键删除许可
	 * @param: id 许可主键
	 * @return: int 影响/删除的记录数
	 * @throws: 无
	 * @date: 2019年12月22日 下午2:18:58
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * @Title: queryPermissionById
	 * @Description: 根据主键查询许可
	 * @param: id 主键
	 * @return: TPermission 许可
	 * @throws: 无
	 * @date: 2019年12月22日 下午2:42:03
	 */
	TPermission queryPermissionById(Integer id);

	/**
	 * @Title: queryPermissionIdsByRoleId
	 * @Description: 通过角色主键查询许可主键
	 * @param: roleId 角色主键
	 * @return: List<Integer> 许可主键id列表
	 * @throws: 无
	 * @date: 2019年12月25日 下午7:16:08
	 */
	List<Integer> queryPermissionIdsByRoleId(Integer roleId);

	/**
	 * @Title: saveRolePermissions
	 * @Description: 保存角色许可
	 * @param: roleId 角色id
	 * @param: newPids 许可id列表
	 * @return: void 空
	 * @throws: 无
	 * @date: 2019年12月25日 下午8:15:49
	 */
	void saveRolePermissions(@Param("roleId")Integer roleId,@Param("newPids")List<Integer> newPids);

	/**
	 * @Title: deleteRolePermissions
	 * @Description: 删除角色许可
	 * @param: roleId 角色id
	 * @param: oldPids 许可id列表
	 * @return: void 空
	 * @throws: 无
	 * @date: 2019年12月25日 下午8:16:27
	 */
	void deleteRolePermissions(@Param("roleId")Integer roleId,@Param("oldPids")List<Integer> oldPids);
}