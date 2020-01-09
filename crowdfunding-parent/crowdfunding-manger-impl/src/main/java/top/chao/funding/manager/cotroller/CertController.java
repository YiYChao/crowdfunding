package top.chao.funding.manager.cotroller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import top.chao.funding.bean.TCert;
import top.chao.funding.exception.LoginFailException;
import top.chao.funding.manager.service.CertService;
import top.chao.funding.util.AjaxResult;
import top.chao.funding.util.PageResult;
import top.chao.funding.util.StringUtil;

/**
 * @ClassName: CertController  
 * @Description: 资质管理前端控制器 
 * @author: YiYChao
 * @date 2019年12月27日 下午9:08:48
 */
@Controller
@RequestMapping(value="/cert")
public class CertController {

	@Autowired
	private CertService certService;
	
	@RequestMapping(value="/index")
	public String toIndex() {
		return "/cert/cert";
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public AjaxResult queryList() {
		AjaxResult result = new AjaxResult();
		try {
			PageResult<TCert> page = new PageResult<TCert>();	// 创建分页结果封装对象page
			page.setCurrentPage(1);
			page.setPageSize(10);
			
			List<TCert> rstList = certService.queryAllList();	// 查询全部的资质信息
			page.setResultList(rstList);	// 将查询结果封装进page对象
			result.setObj(page);
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMessage("系统异常，查询资质信息失败！");
		}
		return result;
	}
	
	@RequestMapping(value="/add")
	public String toAdd() {
		return "/cert/add";
	}
	
	@RequestMapping(value="/doAdd")
	@ResponseBody
	public AjaxResult doAdd(String name) {
		AjaxResult result = new AjaxResult();
		int rst = certService.insertCert(name);
		if(rst == 1) {
			result.setSuccess(true);
		}else {
			result.setSuccess(false);
			result.setMessage("系统异常，新增资质信息失败！");
		}
		return result;
	}
	
	@RequestMapping(value="/edit")
	public String toEdit(Integer id, Map<String,TCert> map) {
		if (id != null) {
			TCert cert = certService.queryCertById(id);
			map.put("cert", cert);
		}else {
			throw new LoginFailException("资质有误!");
		}
		return "/cert/edit";
	}
	
	@RequestMapping(value="/doEdit")
	@ResponseBody
	public AjaxResult doEdit(TCert cert) {
		AjaxResult result = new AjaxResult();
		try {
			if (cert.getId() != null && StringUtil.isNotEmpty(cert.getName())) {
				int rst = certService.updateCert(cert);
				if(rst == 1) {
					result.setSuccess(true);
				}else {
					result.setSuccess(false);
				}
			}else {
				result.setSuccess(false);
			}
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMessage("系统异常，修改资质信息失败！");
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping(value="/doDelete")
	@ResponseBody
	public AjaxResult doDelete(Integer id) {
		AjaxResult result = new AjaxResult();
		try {
			int rst = certService.deleteCertById(id);
			if(rst == 1) {
				result.setSuccess(true);
			}else {
				result.setSuccess(false);
			}
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMessage("系统异常，删除资质信息失败！");
			e.printStackTrace();
		}
		return result;
	}
	
	
}
