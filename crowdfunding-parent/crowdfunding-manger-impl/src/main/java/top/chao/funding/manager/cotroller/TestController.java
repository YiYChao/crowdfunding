package top.chao.funding.manager.cotroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import top.chao.funding.manager.service.TestService;

@Controller
public class TestController {

	@Autowired
	private TestService testService;
	
	@RequestMapping("/test")
//	@ResponseBody
	public String testController() {
		System.out.println("TestController");
		testService.insert();
		return "success";
	}
}
