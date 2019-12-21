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

}