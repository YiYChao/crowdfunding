package top.chao.funding.manager.service;

import java.util.List;

import top.chao.funding.bean.TAccountTypeCert;
import top.chao.funding.bean.TCert;

/**
 * @ClassName: AccTypeCertService  
 * @Description: 用户类型资质关系相关接口定义 
 * @author: YiYChao
 * @date 2020年1月10日 下午4:50:25
 */
public interface AccTypeCertService {

	/**
	 * @Title: insertAccTypeCert
	 * @Description: 保存分类关系
	 * @param: actypcert 分类实体
	 * @return: int 新增的记录数
	 * @throws: 无
	 * @date: 2020年1月10日 下午4:51:19
	 */
	int insertAccTypeCert(TAccountTypeCert actypcert);

	/**
	 * @Title: deleteAccTypeCert
	 * @Description: 删除分类关系
	 * @param: actypcert 分类实体
	 * @return: int 删除的记录数
	 * @throws: 无
	 * @date: 2020年1月10日 下午4:51:45
	 */
	int deleteAccTypeCert(TAccountTypeCert actypcert);
	
	/**
	 * @Title: queryAll
	 * @Description: 查询所有的分类关系
	 * @return: List<TAccountTypeCert> 分类关系实体列表
	 * @throws: 无
	 * @date: 2020年1月10日 下午4:56:40
	 */
	List<TAccountTypeCert> queryAll();

	/**
	 * @Title: queryCertNeeded
	 * @Description: 根据账户类型获取所需认证的资质类型
	 * @param: accttype 账户类型
	 * @return: List<TCert> 资质列表
	 * @throws: 无
	 * @date: 2020年1月11日 下午3:21:27
	 */
	List<TCert> queryCertNeeded(String accttype);

}
