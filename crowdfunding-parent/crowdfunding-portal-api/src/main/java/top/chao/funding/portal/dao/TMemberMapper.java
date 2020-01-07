package top.chao.funding.portal.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import top.chao.funding.bean.TMember;
import top.chao.funding.bean.TMemberExample;

public interface TMemberMapper {
    long countByExample(TMemberExample example);

    int deleteByExample(TMemberExample example);

    int insert(TMember record);

    int insertSelective(TMember record);

    List<TMember> selectByExample(TMemberExample example);

    int updateByExampleSelective(@Param("record") TMember record, @Param("example") TMemberExample example);

    int updateByExample(@Param("record") TMember record, @Param("example") TMemberExample example);

    /**
     * @Title: queryMemberLogin
     * @Description: 查询会员是否登录
     * @param: memberMap 会员信息映射
     * @return: TMember 会员实体
     * @throws: 无
     * @date: 2020年1月7日 下午8:41:10
     */
	TMember queryMemberLogin(Map<String, Object> memberMap);
	
}