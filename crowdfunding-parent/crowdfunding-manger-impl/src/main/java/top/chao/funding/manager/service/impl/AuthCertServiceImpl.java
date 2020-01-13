package top.chao.funding.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.chao.funding.bean.TMember;
import top.chao.funding.bean.TTicket;
import top.chao.funding.manager.service.AuthCertService;
import top.chao.funding.portal.dao.TMemberMapper;
import top.chao.funding.vo.MemberCertVO;
/**
 * @ClassName: AuthCertServiceImpl  
 * @Description: 会员实名认证审核相关操作接口实现
 * @author: YiYChao
 * @date 2020年1月12日 下午2:32:35
 */
@Service
public class AuthCertServiceImpl implements AuthCertService{

	@Autowired
	private TMemberMapper tMemberMapper;
	
	@Override
	public TTicket queryTicketByPiid(String piid) {
		return tMemberMapper.queryTicketByPiid(piid);
	}

	@Override
	public TMember queryMemberById(int memberid) {
		return tMemberMapper.queryMemberById(memberid);
	}

	@Override
	public List<MemberCertVO> queryMemberCertByMemberid(Integer memberid) {
		return tMemberMapper.queryMemberCertByMemberid(memberid);
	}

}
