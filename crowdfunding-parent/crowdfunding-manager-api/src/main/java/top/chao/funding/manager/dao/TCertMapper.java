package top.chao.funding.manager.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.chao.funding.bean.TCert;
import top.chao.funding.bean.TCertExample;

public interface TCertMapper {
    long countByExample(TCertExample example);

    int deleteByExample(TCertExample example);

    int insert(TCert record);

    int insertSelective(TCert record);

    List<TCert> selectByExample(TCertExample example);

    int updateByExampleSelective(@Param("record") TCert record, @Param("example") TCertExample example);

    int updateByExample(@Param("record") TCert record, @Param("example") TCertExample example);

    /**
     * @Title: queryCertById
     * @Description: 通过主键查询资质信息
     * @param: id
     * @return: TCert 资质实体
     * @throws: 无
     * @date: 2020年1月9日 下午9:43:18
     */
	TCert queryCertById(Integer id);

	/**
	 * @Title: updateCert
	 * @Description: 更新资质信息
	 * @param: cert 资质实体
	 * @return: int 更新的记录数
	 * @throws: 无
	 * @date: 2020年1月9日 下午10:00:46
	 */
	int updateCert(TCert cert);

	/**
	 * @Title: deleteCertById
	 * @Description: 通过主键删除资质
	 * @param: id 资质主键
	 * @return: int 删除的主键数
	 * @throws: 无
	 * @date: 2020年1月9日 下午10:07:59
	 */
	int deleteCertById(Integer id);
}