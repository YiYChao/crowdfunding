package top.chao.funding.util;
/**
 * @ClassName: StringUtil  
 * @Description: 定义空字符串的判断工具类 
 * @author: YiYChao
 * @date 2019年12月17日 下午11:13:32
 *
 */
public class StringUtil {

	public static boolean isEmpty(String s) {
		return s == null || "".equals(s); // s == null | s.equals("");
	}

	public static boolean isNotEmpty(String s) {
		return !isEmpty(s);
	}
}
