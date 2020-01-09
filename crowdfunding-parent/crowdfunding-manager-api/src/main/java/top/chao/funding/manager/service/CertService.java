package top.chao.funding.manager.service;

import java.util.List;

import top.chao.funding.bean.TCert;

/**
 * @ClassName: CertService  
 * @Description: 资质相关的接口定义 
 * @author: YiYChao
 * @date 2020年1月9日 下午4:59:47
 */
public interface CertService {

	/**
	 * @Title: queryAllList
	 * @Description: 查询所有的资质信息
	 * @return: List<TCert> 资质列表
	 * @throws: 无
	 * @date: 2020年1月9日 下午5:01:18
	 */
	List<TCert> queryAllList();

	/**
	 * @Title: insertCert
	 * @Description: 新增资质记录
	 * @param: name 资质名称
	 * @return: int 新增的记录数
	 * @throws: 无
	 * @date: 2020年1月9日 下午6:13:36
	 */
	int insertCert(String name);

	/**
	 * @Title: queryCertById
	 * @Description: 通过主键查询资质信息
	 * @param: id 资质主键
	 * @return: TCert 资质实体
	 * @throws: 无
	 * @date: 2020年1月9日 下午9:40:00
	 */
	TCert queryCertById(Integer id);

	/**
	 * @Title: updateCert
	 * @Description: 更新资质信息
	 * @param: cert 资质实体
	 * @return: int 更新的实体记录数
	 * @throws: 无
	 * @date: 2020年1月9日 下午9:57:34
	 */
	int updateCert(TCert cert);

	/**
	 * @Title: deleteCertById
	 * @Description: 通过主键删除资质信息
	 * @param: id 资质主键
	 * @return: int 删除的记录数
	 * @throws: 无
	 * @date: 2020年1月9日 下午10:06:42
	 */
	int deleteCertById(Integer id);
}
