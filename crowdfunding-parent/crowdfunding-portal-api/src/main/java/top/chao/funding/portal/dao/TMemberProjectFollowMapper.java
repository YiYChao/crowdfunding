package top.chao.funding.portal.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.chao.funding.bean.TMemberProjectFollow;
import top.chao.funding.bean.TMemberProjectFollowExample;

public interface TMemberProjectFollowMapper {
    long countByExample(TMemberProjectFollowExample example);

    int deleteByExample(TMemberProjectFollowExample example);

    int insert(TMemberProjectFollow record);

    int insertSelective(TMemberProjectFollow record);

    List<TMemberProjectFollow> selectByExample(TMemberProjectFollowExample example);

    int updateByExampleSelective(@Param("record") TMemberProjectFollow record, @Param("example") TMemberProjectFollowExample example);

    int updateByExample(@Param("record") TMemberProjectFollow record, @Param("example") TMemberProjectFollowExample example);
}