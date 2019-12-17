package top.chao.funding.util;
/**
 * @ClassName: AjaxResult  
 * @Description: 创建Ajax请求的返回实体 
 * @author: YiYChao
 * @date 2019年12月17日 下午12:31:46
 */
public class AjaxResult {

	private boolean success;	// 是否成功
	private String message;		// 结果信息
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
