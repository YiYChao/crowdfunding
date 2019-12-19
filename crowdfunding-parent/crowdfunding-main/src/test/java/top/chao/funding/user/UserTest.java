package top.chao.funding.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import top.chao.funding.bean.TUser;
import top.chao.funding.manager.service.UserService;
import top.chao.funding.util.MD5Util;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring*.xml")
public class UserTest {

	
	@Autowired
	private UserService userService;
	
	@Test
	public void testSaveUser() {
		for(int i = 1; i <=100; i++) {
			TUser user =new TUser();
			user.setUsername("test" + i);
			user.setLoginacct("test" + i);
			user.setEmail("yyc" + i + "@chao.cn");
			user.setUserpswd(MD5Util.digest("12345"));
			user.setCreatetime("2019-12-19 14:12:13");
			userService.saveUser(user);
		}
	}
	
}
