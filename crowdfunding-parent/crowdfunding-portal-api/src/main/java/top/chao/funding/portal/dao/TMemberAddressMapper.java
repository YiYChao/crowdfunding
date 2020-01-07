package top.chao.funding.portal.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.chao.funding.bean.TMemberAddress;
import top.chao.funding.bean.TMemberAddressExample;

public interface TMemberAddressMapper {
    long countByExample(TMemberAddressExample example);

    int deleteByExample(TMemberAddressExample example);

    int insert(TMemberAddress record);

    int insertSelective(TMemberAddress record);

    List<TMemberAddress> selectByExample(TMemberAddressExample example);

    int updateByExampleSelective(@Param("record") TMemberAddress record, @Param("example") TMemberAddressExample example);

    int updateByExample(@Param("record") TMemberAddress record, @Param("example") TMemberAddressExample example);
}