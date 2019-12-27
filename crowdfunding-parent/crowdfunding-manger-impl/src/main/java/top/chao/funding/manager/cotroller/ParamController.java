package top.chao.funding.manager.cotroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: ParamController  
 * @Description: 参数管理前端控制器 
 * @author: YiYChao
 * @date 2019年12月27日 下午9:10:42
 */
@Controller
@RequestMapping(value="/param")
public class ParamController {

	@RequestMapping(value="/index")
	public String toIndex() {
		return "/param/param";
	}
}
