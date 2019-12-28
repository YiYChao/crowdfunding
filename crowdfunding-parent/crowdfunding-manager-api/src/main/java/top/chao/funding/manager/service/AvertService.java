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
}
