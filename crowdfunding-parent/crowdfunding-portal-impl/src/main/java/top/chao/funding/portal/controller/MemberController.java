package top.chao.funding.portal.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import top.chao.funding.bean.TMember;
import top.chao.funding.bean.TTicket;
import top.chao.funding.portal.service.TicketService;
import top.chao.funding.util.Const;
/**
 * @ClassName: MemberController  
 * @Description: 会员相关操作的前端控制器 
 * @author: YiYChao
 * @date 2020年1月7日 下午9:09:52
 */
@Controller
@RequestMapping(value="/member")
public class MemberController {

	@Autowired
	private TicketService ticketService;
	
	@RequestMapping("/index")
	public String toIndex() {
		return "member/member";
	}
	
	@RequestMapping("/apply")
	public String toApply(HttpSession session) {
		TMember member = (TMember) session.getAttribute(Const.LOGIN_MEMBER);
		TTicket ticket = ticketService.queryTicketByMemberId(member.getId());
		if(ticket == null) {
			ticketService.saveTicket(member.getId());
			return "member/accttype";
		}else {
			if("accttype".equals(ticket.getPstep())) {
				return "/member/accttype";
			}else if("basicinfo".equals(ticket.getPstep())) {
				return "/member/basicinfo";
			}else if("uploadfile".equals(ticket.getPstep())) {
				return "/member/uploadfile";
			}else if("checkemail".equals(ticket.getPstep())) {
				return "/member/checkemail";
			}else {
				return "redirect:/member/index";
			}
		}
	}
	
	@RequestMapping("/accttype")
	public String toAccttype() {
		return "member/accttype";
	}
	
	@RequestMapping("/basicinfo")
	public String toBasicinfo() {
		return "member/basicinfo";
	}
	
	@RequestMapping("/uploadfile")
	public String toUploadfile() {
		return "member/uploadfile";
	}
	
	@RequestMapping("/checkemail")
	public String toCheckemail() {
		return "member/checkemail";
	}
}
