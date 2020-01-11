package top.chao.funding.manager.cotroller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import top.chao.funding.bean.TAccountTypeCert;
import top.chao.funding.bean.TCert;
import top.chao.funding.bean.TMember;
import top.chao.funding.manager.service.AccTypeCertService;
import top.chao.funding.manager.service.CertService;
import top.chao.funding.util.AjaxResult;
import top.chao.funding.util.Const;

/**
 * @ClassName: AccTypeCertController  
 * @Description: 用户类型资质管理前端控制器 
 * @author: YiYChao
 * @date 2020年1月10日 下午2:59:15
 */
@Controller
@RequestMapping(value="/acctypecert")
public class AccTypeCertController {

	@Autowired
	private CertService certService;
	@Autowired
	private AccTypeCertService accTypeCertService;
	
	
	@RequestMapping(value="/index")
	public String toIndex() {
		return "acctypecert/acctypecert";
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	@ResponseBody
	public AjaxResult getList() {
		AjaxResult result = new AjaxResult();
		try {
			List<TCert> allList = certService.queryAllList();
			result.setObj(allList);
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMessage("系统异常，加载类型矩阵数据失败！");
		}
		return result;
	}
	
	@RequestMapping(value="/saveAcctTypeCert",method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult saveAcctTypeCert(TAccountTypeCert actypcert) {
		AjaxResult result = new AjaxResult();
		try {
			int rst = accTypeCertService.insertAccTypeCert(actypcert);
			if(rst == 1) {
				result.setSuccess(true);
			}else {
				result.setSuccess(false);
				result.setMessage("数据异常，保存分类关系失败！");
			}
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMessage("系统异常，保存分类关系失败！");
		}
		return result;
	}
	
	@RequestMapping(value="/deleteAcctTypeCert", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult deleteAcctTypeCert(TAccountTypeCert actypcert) {
		AjaxResult result = new AjaxResult();
		try {
			int rst = accTypeCertService.deleteAccTypeCert(actypcert);
			if(rst == 1) {
				result.setSuccess(true);
			}else {
				result.setSuccess(false);
				result.setMessage("数据异常，删除分类关系失败！");
			}
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMessage("系统异常，删除分类关系失败！");
		}
		return result;
	}
	
	
	@RequestMapping(value="/updateRelations", method=RequestMethod.GET)
	@ResponseBody
	public AjaxResult updateRelations() {
		AjaxResult result = new AjaxResult();
		try {
			List<TAccountTypeCert> list = accTypeCertService.queryAll();
			result.setObj(list);
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMessage("系统异常，更新分类关系失败！");
		}
		return result;
	}
	
	@RequestMapping(value="/picneed")
	@ResponseBody
	public AjaxResult getAcctTypePic(HttpSession session,Map<String, List<TCert>> map) {
		AjaxResult result = new AjaxResult();
		try {
			TMember member = (TMember) session.getAttribute(Const.LOGIN_MEMBER);	//获取会员的基本信息
			List<TCert> certList = accTypeCertService.queryCertNeeded(member.getAccttype());
			map.put("certList", certList);
			result.setObj(certList);
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMessage("系统异常，获取所需资质类别失败！");
		}
		return result;
	}
	
}
