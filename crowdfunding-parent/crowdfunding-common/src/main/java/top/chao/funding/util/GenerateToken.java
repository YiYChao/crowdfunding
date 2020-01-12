package top.chao.funding.util;
/**
 * @ClassName: GenerateToken  
 * @Description: 生成随机的字符串 
 * @author: YiYChao
 * @date 2020年1月12日 上午9:25:33
 */
public class GenerateToken {
	/**
	 * @Title: getToken
	 * @Description: 生成自定义长度的数字和字母组合的验证码
	 * @param: len 验证码长度
	 * @return: String 生成的验证码
	 * @throws: 无
	 * @date: 2020年1月12日 上午9:27:04
	 */
	public String getToken(Integer len) {
		// 开辟16位空间，用于存储产生的随机字符
		char[] res = new char[len];
		for (int i = 0; i < len; i++) {
			// Token包含的字符有： 0~9，A~Z，总共有10+26=36个数字
			double random = Math.random()*36;
			if(random < 10) {
				// 数字对应的ASCII码值为48~57
				res[i] = (char)(48 + Math.random()*10);
			}else {
				// 大写英文字母对应的ASCII码值为65~90
				res[i] = (char)(65 + Math.random()*26);
			}
		}
		return String.valueOf(res);
	}
}
