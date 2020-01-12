package top.chao.funding.portal.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import top.chao.funding.bean.TMember;
import top.chao.funding.bean.TTicket;
import top.chao.funding.portal.activiti.AuthFailListener;
import top.chao.funding.portal.activiti.AuthPassListener;
import top.chao.funding.portal.service.MemberService;
import top.chao.funding.portal.service.TicketService;
import top.chao.funding.util.AjaxResult;
import top.chao.funding.util.Const;
import top.chao.funding.util.GenerateToken;
import top.chao.funding.util.StringUtil;
import top.chao.funding.vo.DataVO;
import top.chao.funding.vo.MemberCertVO;

/**
 * @ClassName: MenberAuthenController  
 * @Description: 会员认证相关操作的前端控制器 
 * @author: YiYChao
 * @date 2020年1月11日 上午9:28:29
 */
@Controller
@RequestMapping(value="/mbAuthen")
public class MenberAuthenController {
	
	@Autowired
	private MemberService memberService;
	@Autowired
	private TicketService ticketService;
	
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private TaskService taskService;
	
	@RequestMapping(value="/accttype.do")
	@ResponseBody
	public AjaxResult choseAcctType(String accttype, HttpSession session) {
		AjaxResult result = new AjaxResult();
		try {
			TMember member = (TMember) session.getAttribute(Const.LOGIN_MEMBER);	//获取会员的基本信息
			member.setAccttype(accttype);	// 设置会员的认证类型
			int rst = memberService.updateMemberAcctType(member);	// 更新会员信息
			if(rst == 1) {
				// 设置流程审批单
				TTicket ticket = ticketService.queryTicketByMemberId(member.getId());	// 查询流程审批单
				ticket.setPstep("basicinfo");	// 设置要更新的字段
				ticketService.updateTicket(ticket);	// 进行更新
				
				result.setSuccess(true);
			}
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMessage("系统异常，选择认证类型失败！");
		}
		return result;
	}
	
	@RequestMapping(value="/basicinfo.do")
	@ResponseBody
	public AjaxResult checkBasicInfo(String realname,String cardnum,String telephone, HttpSession session) {
		AjaxResult result = new AjaxResult();
		try {
			TMember member = (TMember) session.getAttribute(Const.LOGIN_MEMBER);	//获取会员的基本信息
			member.setRealname(realname);	// 设置会员的基本信息
			member.setCardnum(cardnum);
			member.setTelephone(telephone);
			
			int rst = memberService.updateMemberAcctType(member);	// 更新会员信息
			if(rst == 1) {
				// 设置流程审批单
				TTicket ticket = ticketService.queryTicketByMemberId(member.getId());	// 查询流程审批单
				ticket.setPstep("uploadfile");	// 设置要更新的字段
				ticketService.updateTicket(ticket);	// 进行更新
				
				result.setSuccess(true);
			}
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMessage("系统异常，确认基本信息失败！");
		}
		return result;
	}
	
	@RequestMapping(value="/uploadMemberCertFile.do")
	@ResponseBody
	public AjaxResult uploadMemberCertFile(DataVO dv, HttpSession session) {
		AjaxResult result = new AjaxResult();
		try {
			TMember member = (TMember) session.getAttribute(Const.LOGIN_MEMBER);	//获取会员的基本信息
			
			ServletContext application = session.getServletContext();	// 获得容器对象
			String realPath = application.getRealPath("pic");	// 获取保存路径
			
			// 将上传的文件保存起来，让管理用户进行审核
			for ( MemberCertVO mcv : dv.getMembercert()) {
				mcv.setMemberid(member.getId());
				// 保存图片文件
				MultipartFile file = mcv.getImgfile();
				String oldFileName = file.getOriginalFilename();
				String fileName = UUID.randomUUID().toString() + oldFileName.substring(oldFileName.lastIndexOf("."));	// 形成最终文件名
				mcv.setIconpath(fileName);	// 更新字段
				String filePath = realPath + "\\cert\\" + fileName;	// 图片保存的绝对路径
				
				file.transferTo(new File(filePath));
			}
			
			memberService.saveMemberCertList(dv.getMembercert());	// 将记录保存到数据库
			
			TTicket ticket = ticketService.queryTicketByMemberId(member.getId());	// 查询流程审批单
			ticket.setPstep("checkemail");	// 设置要更新的字段
			ticketService.updateTicket(ticket);	// 进行更新
			
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("系统异常，保存会员资质信息失败！");
		}
		return result;
	}
	
	@RequestMapping(value="/checkemail.do")
	@ResponseBody
	public AjaxResult checkEmail(String email, HttpSession session) {
		AjaxResult result = new AjaxResult();
		try {
			TMember member = (TMember) session.getAttribute(Const.LOGIN_MEMBER);	//获取会员的基本信息
			if(StringUtil.isNotEmpty(email)) {
				// 1、会员修改了邮箱
				if(!email.equals(member.getEmail())) {
					member.setEmail(email);
					memberService.updateMemberAcctType(member);	// 更新会员信息
				}
				// 2、启动流程
				// 2.1、查找到需要启动的流程的定义
				ProcessDefinitionQuery definitionQuery = repositoryService.createProcessDefinitionQuery();
				ProcessDefinition mailProcess = definitionQuery.processDefinitionKey("AuthenProcess").latestVersion().singleResult();
				// 2.2、设置流程的参数
				Map<String,Object> variables = new HashMap<String,Object>();
				variables.put("memberEmail", member.getEmail());
				variables.put("memberName", member.getRealname());
				String authCode = new GenerateToken().getToken(6);
				variables.put("authCode", authCode);
				variables.put("loginacct", member.getLoginacct());
				variables.put("passListener", new AuthPassListener());
				variables.put("refuseListener", new AuthFailListener());

				ProcessInstance processInstance = runtimeService.startProcessInstanceById(mailProcess.getId(), variables);
				
				
				// 设置流程审批单
				TTicket ticket = ticketService.queryTicketByMemberId(member.getId());	// 查询流程审批单
				ticket.setPstep("checkauthcode");	// 设置要更新的字段
				ticket.setPiid(processInstance.getId());	// 设置流程实例id
				ticket.setAuthcode(authCode);	// 记录验证码
				ticketService.updateTicket(ticket);	// 进行更新
				
				result.setSuccess(true);
			}else {
				result.setSuccess(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("系统异常，确认邮箱信息失败！");
		}
		return result;
	}
	
	@RequestMapping(value="/finishApply.do")
	@ResponseBody
	public AjaxResult finishApply(String authcode, HttpSession session) {
		AjaxResult result = new AjaxResult();
		try {
			TMember member = (TMember) session.getAttribute(Const.LOGIN_MEMBER);	//获取会员的基本信息
			TTicket ticket = ticketService.queryTicketByMemberId(member.getId());	// 查询流程审批单
			if(StringUtil.isNotEmpty(authcode)) {
				// 1、验证码正确
				if(authcode.equals(ticket.getAuthcode())) {
					// 2、 完成任务
					TaskQuery query = taskService.createTaskQuery();
					Task task = query.processInstanceId(ticket.getPiid()).taskAssignee(member.getLoginacct()).singleResult();
					taskService.complete(task.getId()); 	// 完成任务
					
					// 3、更新会员状态
					member.setAuthstatus("1"); //修改会员申请状态   (0 - 未实名认证， 1 - 实名认证申请中， 2 - 已实名认证)
					memberService.updateMemberAcctType(member);	// 更新会员信息
					
					// 4、修改实名认证审批单
					ticket.setStatus("1");
					ticket.setPstep("finishApply");
					ticketService.updateTicket(ticket);	// 进行更新
					
					result.setSuccess(true);
				}else {
					result.setSuccess(false);
					result.setMessage("验证码输入不正确！");
				}
			}else {
				result.setSuccess(false);
				result.setMessage("验证码不能为空！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("系统异常，确认邮箱信息失败！");
		}
		return result;
	}
}
