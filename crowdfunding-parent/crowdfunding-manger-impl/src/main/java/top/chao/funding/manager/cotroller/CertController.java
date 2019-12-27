package top.chao.funding.manager.cotroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: CertController  
 * @Description: 资质管理前端控制器 
 * @author: YiYChao
 * @date 2019年12月27日 下午9:08:48
 */
@Controller
@RequestMapping(value="/cert")
public class CertController {

	@RequestMapping(value="/index")
	public String toIndex() {
		return "/cert/cert";
	}
}
