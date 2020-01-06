package top.chao.funding.manager.cotroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import top.chao.funding.util.AjaxResult;
import top.chao.funding.util.PageResult;

/**
 * @ClassName: ProcessController  
 * @Description: TODO 
 * @author: YiYChao
 * @date 2020年1月6日 下午9:04:51
 */
@Controller
@RequestMapping(value="/process")
public class ProcessController {

	@Autowired
	private RepositoryService repositoryService;
	
	@RequestMapping(value="/index.html")
	public String toIndex() {
		return "/process/process";
	}
	@RequestMapping("/list")
	@ResponseBody
	public AjaxResult getList(@RequestParam(value="currentPage", required=false, defaultValue="1") Integer currentPage,
			@RequestParam(value="pageSizes", required=false, defaultValue="3") Integer pageSizes, String condition) {
		AjaxResult result = new AjaxResult();
		try {
			PageResult<Map<String,Object>> page = new PageResult<Map<String,Object>>();
			page.setCurrentPage(currentPage);
			page.setPageSize(pageSizes);
			ProcessDefinitionQuery definitionQuery = repositoryService.createProcessDefinitionQuery();
			
			Long count = definitionQuery.count();
			page.setTotalRecords(count.intValue());
			
			List<ProcessDefinition> listPage = definitionQuery.listPage(page.getBegin(), pageSizes);
			List<Map<String,Object>> reListPage = new ArrayList<Map<String,Object>>();
			for (ProcessDefinition process : listPage) {
				Map<String,Object> prosDifn = new HashMap<String, Object>();
				prosDifn.put("id", process.getId());
				prosDifn.put("name", process.getName());
				prosDifn.put("key", process.getKey());
				prosDifn.put("version", process.getVersion());
				
				reListPage.add(prosDifn);
			}
			
			page.setResultList(reListPage);
			
			result.setPageResult(page);
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMessage("系统异常，查询流程失败！");
		}
		return result;
	}
}
