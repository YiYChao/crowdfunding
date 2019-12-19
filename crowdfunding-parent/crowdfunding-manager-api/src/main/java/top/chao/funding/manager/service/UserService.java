package top.chao.funding.manager.service;

import java.util.Map;

import top.chao.funding.bean.TUser;
import top.chao.funding.util.PageResult;

/**
 * @ClassName: UserService  
 * @Description: 定义用户相关的操作的接口 
 * @author: YiYChao
 * @date 2019年12月16日 下午7:31:38
 */
public interface UserService {

	TUser queryUserLogin(Map<String, Object> userMap);

	PageResult<TUser> queryPage(Integer currentPage, Integer pageSizes);

	/**
	 * @Title: saveUser
	 * @Description: 保存用户
	 * @param: user 用户实体对象
	 * @return: void 无
	 * @throws: 无
	 * @date: 2019年12月19日 下午2:13:05
	 */
	void saveUser(TUser user);

}
