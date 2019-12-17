package top.chao.funding.manager.service;

import java.util.Map;

import top.chao.funding.bean.TUser;

/**
 * @ClassName: UserService  
 * @Description: 定义用户相关的操作的接口 
 * @author: YiYChao
 * @date 2019年12月16日 下午7:31:38
 */
public interface UserService {

	TUser queryUserLogin(Map<String, Object> userMap);

}
