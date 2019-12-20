package top.chao.funding.manager.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import top.chao.funding.bean.TUser;
import top.chao.funding.bean.TUserExample;

public interface TUserMapper {
    long countByExample(TUserExample example);

    int deleteByExample(TUserExample example);

    int insert(TUser record);

    int insertSelective(TUser record);

    List<TUser> selectByExample(TUserExample example);

    int updateByExampleSelective(@Param("record") TUser record, @Param("example") TUserExample example);

    int updateByExample(@Param("record") TUser record, @Param("example") TUserExample example);
    
    TUser queryUserLogin(Map<String, Object> userMap);

    @Deprecated
	int queryTotal();

	@Deprecated
	List<TUser> queryList(@Param("begin")Integer begin, @Param("size")Integer pageSize);

	/**
	 * @Title: queryConditionTotal
	 * @Description: 查询分页条件下的用户总记录数
	 * @param: condition 条件
	 * @return: int 整型页数
	 * @throws: 无
	 * @date: 2019年12月19日 下午8:35:20
	 */
	int queryConditionTotal(String condition);

	/**
	 * @Title: queryConditonList
	 * @Description: 分页条件查询
	 * @param: begin 开始索引
	 * @param: pageSize 查询记录数
	 * @param: condition 条件
	 * @return: List<TUser> 用户列表
	 * @throws: 无
	 * @date: 2019年12月19日 下午8:37:10
	 */
	List<TUser> queryConditonList(@Param("begin")Integer begin, @Param("size")Integer pageSize, @Param("condition")String condition);

	/**
	 * @Title: selectByPrimaryKey
	 * @Description: 通过主键查询用户信息
	 * @param: id 主键
	 * @return: TUser 用户实体
	 * @throws: 无
	 * @date: 2019年12月20日 下午3:18:57
	 */
	TUser selectByPrimaryKey(Integer id);

	/**
	 * @Title: updateUserByPrimaryKey
	 * @Description: 通过主键更新用户信息
	 * @param: user 用书实体
	 * @return: int 更新的记录条数
	 * @throws: 无
	 * @date: 2019年12月20日 下午3:35:41
	 */
	int updateUserByPrimaryKey(TUser user);

	/**
	 * @Title: deleteUserByPrimaryKey
	 * @Description: 根据主键删除用户记录
	 * @param: id 主键
	 * @return: int 影响的记录数
	 * @throws: 无
	 * @date: 2019年12月20日 下午4:13:20
	 */
	int deleteUserByPrimaryKey(Integer id);
}