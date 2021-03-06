package top.chao.funding.manager.cotroller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import top.chao.funding.bean.TAdvertisement;
import top.chao.funding.bean.TUser;
import top.chao.funding.manager.service.AvertService;
import top.chao.funding.util.AjaxResult;
import top.chao.funding.util.Const;
import top.chao.funding.util.PageResult;

/**
 * @ClassName: AdvertController  
 * @Description: 广告管理模块前端控制器 
 * @author: YiYChao
 * @date 2019年12月28日 下午7:01:07
 */
@Controller
@RequestMapping(value="/advert")
public class AdvertController {

	@Autowired
	private AvertService avertService;
	
	@RequestMapping(value="/index")
	public String toIndex() {
		return "/advert/advert";
	}
	
	@RequestMapping(value="/add")
	public String toAdd() {
		return "/advert/add";
	}
	
	@RequestMapping(value="/edit")
	public String toEdit(Integer advertId, Map<String,TAdvertisement> advert) {
		return "/advert/edit";
	}
	
	/**
	 * @Title: getList
	 * @Description: 查询广告列表
	 * @param: currentPage 当前要显示的页
	 * @param: pageSizes 每页记录数
	 * @param: condition 查询条件
	 * @return: AjaxResult 自定义Ajax实体对象
	 * @throws: 无
	 * @date: 2019年12月28日 下午7:13:59
	 */
	@RequestMapping("/list")
	@ResponseBody
	public AjaxResult getList(@RequestParam(value="currentPage", required=false, defaultValue="1") Integer currentPage,
			@RequestParam(value="pageSizes", required=false, defaultValue="3") Integer pageSizes, String condition) {
		AjaxResult result = new AjaxResult();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("currentPage", currentPage);
		map.put("pageSizes", pageSizes);
		map.put("condition", condition);
		
		try {
			result.setSuccess(true);	// 设置查询状态
			PageResult<TAdvertisement> page = avertService.queryConditionPage(map);	// 进行查询
			result.setPageResult(page);	// 设置查询结果
		} catch (Exception e) {
			result.setSuccess(false);	// 设置查询状态
			result.setMessage("获取广告信息失败！");	// 设置失败信息
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping(value="/doAdd")
	public String doAdd(HttpServletRequest requrest, TAdvertisement advert, HttpSession session) {
		AjaxResult result = new AjaxResult();
		try {
			MultipartHttpServletRequest mpRequest = (MultipartHttpServletRequest) requrest;
			MultipartFile file = mpRequest.getFile("advtpic");	// 获取文件
			String name = file.getOriginalFilename();	// 获取文件名
			String extName = name.substring(name.lastIndexOf("."));	// 截取文件扩展名
			String iconpath = UUID.randomUUID().toString() + extName;
			
			String path2 = requrest.getContextPath();
			
			ServletContext context = session.getServletContext();
			String realPath = context.getRealPath("/pic");
			System.out.println("realPath" + realPath +"\n" + path2);
			String path = realPath + "\\adv\\" + iconpath;
			System.out.println("path" + path);
			file.transferTo(new File(path));
			
			TUser user = (TUser) session.getAttribute(Const.LOGIN_USER);
			advert.setUserid(user.getId());
			advert.setStatus("1");
			advert.setIconpath(iconpath);
			
			int res = avertService.saveAdvert(advert);
			result.setSuccess(res == 1);
			
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMessage("新增广告失败！");
			e.printStackTrace();
		}
		return "redirect:/advert/index.html";
	}
	
	@RequestMapping(value="/doDelete")
	@ResponseBody
	public AjaxResult doDelete(Integer id) {
		AjaxResult result = new AjaxResult();
		try {
			int count = avertService.deleteAdvertById(id);
			result.setSuccess(count == 1);
		} catch (Exception e) {
			result.setMessage("删除广告记录失败！");
			e.printStackTrace();
		}
		return result;
	}

	
	
	
}
