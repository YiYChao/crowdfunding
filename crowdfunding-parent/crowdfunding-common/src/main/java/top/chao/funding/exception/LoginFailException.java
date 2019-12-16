package top.chao.funding.exception;
/**
 * @ClassName: LoginFailException  
 * @Description: 自定义用户登录异常类 
 * @author: YiYChao
 * @date 2019年12月16日 下午8:23:48
 */
public class LoginFailException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public LoginFailException(String message) {
		super(message);
	}

}
