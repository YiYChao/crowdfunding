package top.chao.funding.manager.cotroller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
			PageResult<Map<String,Object>> page = new PageResult<Map<String,Object>>();	// 定义分页结果对象
			page.setCurrentPage(currentPage);	// 设置分页参数
			page.setPageSize(pageSizes);
			ProcessDefinitionQuery definitionQuery = repositoryService.createProcessDefinitionQuery();	// 创建流程定义查询对象
			
			Long count = definitionQuery.count();	// 查询总流程记录数
			page.setTotalRecords(count.intValue());
			
			List<ProcessDefinition> listPage = definitionQuery.listPage(page.getBegin(), pageSizes);	// 流程分页查询
			List<Map<String,Object>> reListPage = new ArrayList<Map<String,Object>>();
			for (ProcessDefinition process : listPage) {	// 由于存在查询结果转换JackSON报错，所以手动封装结果对象，
				Map<String,Object> prosDifn = new HashMap<String, Object>();
				prosDifn.put("id", process.getId());
				prosDifn.put("name", process.getName());
				prosDifn.put("key", process.getKey());
				prosDifn.put("version", process.getVersion());
				
				reListPage.add(prosDifn);	// 加入列表
			}
			
			page.setResultList(reListPage);	// 封装进分页对象
			
			result.setPageResult(page);	// 封装进结果对象结果
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMessage("系统异常，查询流程失败！");
		}
		return result;
	}
	
	@RequestMapping(value="/view")
	public String toView() {
		return "process/view";
	}
	
	// 显示图片，需要以流的形式返回
	@RequestMapping(value="/doView")
	public void doView(String processId, HttpServletResponse response) throws IOException {
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processId).singleResult();
		InputStream inputStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), processDefinition.getDiagramResourceName());
		
		ServletOutputStream outputStream = response.getOutputStream();
		
		IOUtils.copy(inputStream, outputStream);	// 通过工具类进行流的转换
	}
	
	@RequestMapping(value="/doDelete")
	@ResponseBody
	public AjaxResult doDelete(String processId) {
		AjaxResult result = new AjaxResult();
		try {
			ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processId).singleResult();
			
			repositoryService.deleteDeployment(processDefinition.getDeploymentId(), true);	// 通过部署id级联删除记录
			
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMessage("系统异常，删除流程失败！");
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping(value="/upload")
	@ResponseBody
	public AjaxResult upload(HttpServletRequest request) {
		AjaxResult result = new AjaxResult();
		try {
			// 上传流程定义文件
			MultipartHttpServletRequest fileRequest =(MultipartHttpServletRequest)request;
			
			MultipartFile file = fileRequest.getFile("procDefFile");
			
			// 部署流程定义文件
			repositoryService.createDeployment()
			    //.addClasspathResource(file.getOriginalFilename())
			    .addInputStream(file.getOriginalFilename(), file.getInputStream()).deploy();
			
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMessage("系统异常，部署流程失败！");
			e.printStackTrace();
		}
		return result;
	}
}
