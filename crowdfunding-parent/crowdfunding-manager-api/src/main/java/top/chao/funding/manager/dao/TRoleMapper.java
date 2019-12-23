package top.chao.funding.manager.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.chao.funding.bean.TRole;
import top.chao.funding.bean.TRoleExample;

public interface TRoleMapper {
    long countByExample(TRoleExample example);

    int deleteByExample(TRoleExample example);

    int insert(TRole record);

    int insertSelective(TRole record);

    List<TRole> selectByExample(TRoleExample example);

    int updateByExampleSelective(@Param("record") TRole record, @Param("example") TRoleExample example);

    int updateByExample(@Param("record") TRole record, @Param("example") TRoleExample example);

    /**
     * @Title: queryRoleAll
     * @Description: 查询所有角色信息
     * @return: List<TRole> 角色列表
     * @throws: 无
     * @date: 2019年12月21日 下午3:02:40
     */
	List<TRole> queryRoleAll();

	/**
	 * @Title: queryConditionTotal
	 * @Description: 条件查询所有的记录数
	 * @param: condition 条件
	 * @return: int 走过记录数
	 * @throws: 无
	 * @date: 2019年12月23日 下午9:44:18
	 */
	int queryConditionTotal(String condition);

	/**
	 * @Title: queryConditonList
	 * @Description: 条件查询角色记录
	 * @param: begin 开始索引
	 * @param: pageSize 页大小
	 * @param: condition 条件
	 * @return: List<TRole> 角色列表
	 * @throws: 无
	 * @date: 2019年12月23日 下午9:44:31
	 */
	List<TRole> queryConditonList(@Param("begin")Integer begin, @Param("size")Integer pageSize, @Param("condition")String condition);

	/**
	 * @Title: selectByPrimaryKey
	 * @Description: 通过主键查询角色
	 * @param: id 角色主键
	 * @return: TRole 角色实体
	 * @throws: 
	 * @date: 2019年12月23日 下午9:44:44
	 */
	TRole selectByPrimaryKey(Integer id);

	/**
	 * @Title: updateRoleByPrimaryKey
	 * @Description: 通过主键更新角色
	 * @param: @param role 角色实体
	 * @return: int 更新的记录数
	 * @throws: 无
	 * @date: 2019年12月23日 下午9:45:01
	 */
	int updateRoleByPrimaryKey(TRole role);

	/**
	 * @Title: deleteUserByPrimaryKey
	 * @Description: 通过主键删除角色
	 * @param: integer 主键
	 * @param: @return
	 * @return: int 删除的记录数
	 * @throws: 无
	 * @date: 2019年12月23日 下午9:45:14
	 */
	int deleteRoleByPrimaryKey(Integer id);
}