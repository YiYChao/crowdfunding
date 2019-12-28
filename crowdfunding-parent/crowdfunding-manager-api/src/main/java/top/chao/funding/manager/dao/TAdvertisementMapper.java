package top.chao.funding.manager.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.chao.funding.bean.TAdvertisement;
import top.chao.funding.bean.TAdvertisementExample;

public interface TAdvertisementMapper {
    long countByExample(TAdvertisementExample example);

    int deleteByExample(TAdvertisementExample example);

    int insert(TAdvertisement record);

    int insertSelective(TAdvertisement record);

    List<TAdvertisement> selectByExample(TAdvertisementExample example);

    int updateByExampleSelective(@Param("record") TAdvertisement record, @Param("example") TAdvertisementExample example);

    int updateByExample(@Param("record") TAdvertisement record, @Param("example") TAdvertisementExample example);

    /**
     * @Title: queryConditionTotal
     * @Description: 条件查询总记录数
     * @param: condition 查询条件
     * @return: int 总记录数
     * @throws: 无
     * @date: 2019年12月28日 下午7:22:11
     */
	int queryConditionTotal(String condition);

	/**
	 * @Title: queryConditonList
	 * @Description: 分页条件查询当前页
	 * @param: begin 开始索引
	 * @param: pageSize 查询的记录条数
	 * @param: condition 查询条件
	 * @return: List<TAdvertisement> 广告实体列表
	 * @throws: 无
	 * @date: 2019年12月28日 下午7:22:48
	 */
	List<TAdvertisement> queryConditonList(@Param("begin")Integer begin, @Param("size")Integer pageSize, @Param("condition")String condition);
}