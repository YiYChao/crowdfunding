package top.chao.funding.manager.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.chao.funding.bean.TAccountTypeCert;
import top.chao.funding.bean.TAccountTypeCertExample;

public interface TAccountTypeCertMapper {
    long countByExample(TAccountTypeCertExample example);

    int deleteByExample(TAccountTypeCertExample example);

    int insert(TAccountTypeCert record);

    int insertSelective(TAccountTypeCert record);

    List<TAccountTypeCert> selectByExample(TAccountTypeCertExample example);

    int updateByExampleSelective(@Param("record") TAccountTypeCert record, @Param("example") TAccountTypeCertExample example);

    int updateByExample(@Param("record") TAccountTypeCert record, @Param("example") TAccountTypeCertExample example);

    /**
     * @Title: deleteByAccTypeCert
     * @Description: 删除分类关系
     * @param: actypcert 分类实体
     * @return: int 删除的记录数
     * @throws: 无
     * @date: 2020年1月10日 下午4:55:10
     */
	int deleteByAccTypeCert(TAccountTypeCert actypcert);

	/**
	 * @Title: queryAll
	 * @Description: 查询所有的用户类型资质分类关系
	 * @return: List<TAccountTypeCert> 分类关系实体列表
	 * @throws: 无
	 * @date: 2020年1月10日 下午4:57:54
	 */
	List<TAccountTypeCert> queryAll();
}