package top.chao.funding.manager.service;

import java.util.Map;

import top.chao.funding.bean.TAdvertisement;
import top.chao.funding.util.PageResult;

/**
 * @ClassName: AvertService  
 * @Description: 广告相关操作接口定义 
 * @author: YiYChao
 * @date 2019年12月28日 下午7:16:42
 */
public interface AvertService {

	/**
	 * @Title: queryConditionPage
	 * @Description: 分页查询广告列表
	 * @param: map 查询条件集合
	 * @return: PageResult<TAdvertisement>
	 * @throws: 无
	 * @date: 2019年12月28日 下午7:19:00
	 */
	PageResult<TAdvertisement> queryConditionPage(Map<String, Object> map);

	/**
	 * @Title: saveAdvert
	 * @Description: 保存广告
	 * @param: advert 广告实体
	 * @return: int 保存的记录数
	 * @throws: 无
	 * @date: 2019年12月30日 下午9:21:58
	 */
	int saveAdvert(TAdvertisement advert);

	/**
	 * @Title: deleteAdvertById
	 * @Description: 根据广告主键删除广告
	 * @param: @param id
	 * @param: @return
	 * @return: int
	 * @throws: 无
	 * @date: 2019年12月31日 下午11:07:02
	 */
	int deleteAdvertById(Integer id);
}
