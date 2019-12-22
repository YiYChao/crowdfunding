package top.chao.funding.manager.service;

import java.util.List;

import top.chao.funding.bean.TPermission;

/**
 * @ClassName: PermissionService  
 * @Description: 许可维护相关的接口定义 
 * @author: YiYChao
 * @date 2019年12月22日 上午8:47:43
 */
public interface PermissionService {

	/**
	 * @Title: queryAll
	 * @Description: 查找所有的许可记录
	 * @return: List<TPermission> 返回许可记录列表
	 * @throws: 无
	 * @date: 2019年12月22日 上午8:55:39
	 */
	List<TPermission> queryAll();

	/**
	 * @Title: savePermission
	 * @Description: 添加许可
	 * @param: permission 许可实体
	 * @return: int 影响的记录数
	 * @throws: 无
	 * @date: 2019年12月22日 下午2:01:17
	 */
	int savePermission(TPermission permission);

	/**
	 * @Title: updatePermission
	 * @Description: 修改许可
	 * @param: permission 许可实体
	 * @return: int 影响的实体
	 * @throws: 无
	 * @date: 2019年12月22日 下午2:02:45
	 */
	int updatePermission(TPermission permission);

	/**
	 * @Title: deleteByPrimaryKey
	 * @Description: 根据主键删除许可
	 * @param: id 许可主键
	 * @return: int 影响的记录数
	 * @throws: 无
	 * @date: 2019年12月22日 下午2:17:58
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * @Title: queryPermissionById
	 * @Description: 根据主键查询许可
	 * @param: id 许可主键
	 * @return: TPermission 许可实体
	 * @throws: 无
	 * @date: 2019年12月22日 下午2:40:55
	 */
	TPermission queryPermissionById(Integer id);

}
