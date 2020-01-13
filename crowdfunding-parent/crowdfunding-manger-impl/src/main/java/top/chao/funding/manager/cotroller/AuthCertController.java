package top.chao.funding.manager.cotroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import top.chao.funding.bean.TMember;
import top.chao.funding.bean.TTicket;
import top.chao.funding.manager.service.AuthCertService;
import top.chao.funding.util.AjaxResult;
import top.chao.funding.util.PageResult;
import top.chao.funding.vo.MemberCertVO;

/**
 * @ClassName: AuthCertController  
 * @Description: 实名认证审核相关操作的接口定义 
 * @author: YiYChao
 * @date 2020年1月12日 下午1:44:10
 */
@Controller
@RequestMapping(value="/authcert")
public class AuthCertController {

	@Autowired
	private TaskService taskService;
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private AuthCertService authCertService;
	
	@RequestMapping(value="/index")
	public String toIndex() {
		return "authcert/authcert";
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public AjaxResult queryList(@RequestParam(value="currentPage", required=false, defaultValue="1") Integer currentPage,
			@RequestParam(value="pageSizes", required=false, defaultValue="3") Integer pageSizes) {
		AjaxResult result = new AjaxResult();
		try {
			// 查询任务数据
			TaskQuery query = taskService.createTaskQuery();
			List<Task> listPage = query.processDefinitionKey("AuthenProcess").taskCandidateGroup("authgroup")
					.listPage((currentPage-1)*pageSizes, pageSizes);
			
			List<Map<String, Object>> taskMapList = new ArrayList<Map<String, Object>>();//避免JSON数据转换出错
			
			for (Task task : listPage) {
				Map<String, Object> taskMap = new HashMap<String, Object>();
				taskMap.put("id", task.getId());
				taskMap.put("name", task.getName());
				//通过任务表的流程定义id查询流程定义
				ProcessDefinition pd = repositoryService.createProcessDefinitionQuery()
					    .processDefinitionId(task.getProcessDefinitionId()).singleResult();
				
				taskMap.put("procDefName", pd.getName());
				taskMap.put("procDefVersion", pd.getVersion());
				
				// 通过流程查找流程审批单，再查询会员信息
				TTicket ticket = authCertService.queryTicketByPiid(task.getProcessInstanceId());
				TMember member = authCertService.queryMemberById(ticket.getMemberid());
				taskMap.put("memberName", member.getUsername());
				taskMap.put("memberid", member.getId());
				
				taskMapList.add(taskMap);
			}
			// 获取数据的总条数
			int count = (int)query.count(); //同一个query 对象,查询条件是一样的
			
			PageResult<Map<String,Object>> pageResult = new PageResult<Map<String, Object>>();
			pageResult.setCurrentPage(currentPage);
			pageResult.setPageSize(pageSizes);
			pageResult.setResultList(taskMapList);
			pageResult.setTotalRecords(count);
				
			result.setPageResult(pageResult);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("系统异常，查询实名认证审核信息失败！");
		}
		return result;
	}
	
	@RequestMapping("/view")
	public String show(Integer memberid,Model model) {
		TMember member = authCertService.queryMemberById(memberid);
		
		// 查询当前会员提交资质文件
		List<MemberCertVO> memberCert = authCertService.queryMemberCertByMemberid(memberid);
		
		model.addAttribute("member", member);
		model.addAttribute("memberCert", memberCert);
		
		return "authcert/certview";
	}

	@ResponseBody
	@RequestMapping("/pass")
	public AjaxResult pass( String taskid, Integer memberid ) {
		AjaxResult result = new AjaxResult();
		try {
			taskService.setVariable(taskid, "flag", true);
			taskService.setVariable(taskid, "memberid", memberid);
			// 传递参数，让流程继续执行
			taskService.complete(taskid);
			result.setSuccess(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("系统异常，通过实名认证审核失败！");
		}
		return result;
	}

	@ResponseBody
	@RequestMapping("/refuse")
	public AjaxResult refuse(String taskid, Integer memberid) {
		AjaxResult result = new AjaxResult();
		try {
			taskService.setVariable(taskid, "flag", false);
			taskService.setVariable(taskid, "memberid", memberid);
			// 传递参数，让流程继续执行
			taskService.complete(taskid);
			result.setSuccess(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("系统异常，拒绝实名认证审核失败！");
		}		
		return result;
	}
	
}
