package top.chao.funding.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * @ClassName: MemberController  
 * @Description: 会员相关操作的前端控制器 
 * @author: YiYChao
 * @date 2020年1月7日 下午9:09:52
 */
@Controller
@RequestMapping(value="/member")
public class MemberController {

	@RequestMapping("/index")
	public String toIndex() {
		return "member/member";
	}
	
	
	
	
}
