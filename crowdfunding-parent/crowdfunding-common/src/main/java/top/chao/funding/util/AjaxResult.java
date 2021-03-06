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
	private PageResult<?> pageResult;
	private Object obj;
	
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
	
	public PageResult<?> getPageResult() {
		return pageResult;
	}
	public void setPageResult(PageResult<?> pageResult) {
		this.pageResult = pageResult;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
}
