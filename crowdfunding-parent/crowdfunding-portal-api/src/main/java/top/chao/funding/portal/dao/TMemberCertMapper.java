package top.chao.funding.portal.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.chao.funding.bean.TMemberCert;
import top.chao.funding.bean.TMemberCertExample;

public interface TMemberCertMapper {
    long countByExample(TMemberCertExample example);

    int deleteByExample(TMemberCertExample example);

    int insert(TMemberCert record);

    int insertSelective(TMemberCert record);

    List<TMemberCert> selectByExample(TMemberCertExample example);

    int updateByExampleSelective(@Param("record") TMemberCert record, @Param("example") TMemberCertExample example);

    int updateByExample(@Param("record") TMemberCert record, @Param("example") TMemberCertExample example);
}