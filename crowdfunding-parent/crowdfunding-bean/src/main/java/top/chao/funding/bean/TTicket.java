package top.chao.funding.bean;

public class TTicket {

	private int id;	// 流程审批单主键
	private int memberid;	// 会员id
	private String piid;	// 流程主键
	private String status;	// 流程状态
	private String authcode;	// 验证码
	private String pstep;	// 流程步骤
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMemberid() {
		return memberid;
	}
	public void setMemberid(int memberid) {
		this.memberid = memberid;
	}
	public String getPiid() {
		return piid;
	}
	public void setPiid(String piid) {
		this.piid = piid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAuthcode() {
		return authcode;
	}
	public void setAuthcode(String authcode) {
		this.authcode = authcode;
	}
	public String getPstep() {
		return pstep;
	}
	public void setPstep(String pstep) {
		this.pstep = pstep;
	}
}
