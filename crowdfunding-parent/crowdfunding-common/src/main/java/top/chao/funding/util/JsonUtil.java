package top.chao.funding.util;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * @ClassName: JsonUtil  
 * @Description: JSON与对象相互转换的工具类 
 * @author: YiYChao
 * @date 2020年1月9日 下午2:33:00
 */
public class JsonUtil {

    private static ObjectMapper objectMapper;

    static {
    	if(objectMapper == null) {
    		objectMapper = new ObjectMapper();
    	}
    	
        // 对象的所有字段全部列入
        objectMapper.setSerializationInclusion(Inclusion.ALWAYS);
        // 取消默认转换timestamps形式
        objectMapper.configure(SerializationConfig.Feature.WRITE_DATE_KEYS_AS_TIMESTAMPS, false);
        // 忽略空Bean转Json的错误
        objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
        // 所有日期格式统一为 yyyy:MM:dd HH:mm:ss
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy:MM:dd HH:mm:ss"));
        // 忽略在Json字符串中存在，但在Java对象中不存在的对应属性的状况，防止错误
        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * 对象返回Json字符串
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> String obj2String(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 对象返回格式化后的Json字符串
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> String obj2StringPretty(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (Exception e) {
        	e.printStackTrace();
            return null;
        }
    }

    /**
     * Json字符串转成对象
     * @param str
     * @param clazz
     * @param <T>
     */
    @SuppressWarnings("unchecked")
	public static <T> T string2Obj(String str, Class<T> clazz) {
        if (StringUtils.isEmpty(str) || clazz == null) {
            return null;
        }
        try {
            return clazz.equals(String.class) ? (T) str : objectMapper.readValue(str, clazz);
        } catch (Exception e) {
        	e.printStackTrace();
            return null;
        }
    }

    /**
     * 泛型反序列化
     * @param str
     * @param typeReference 对应返回值的类型
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
	public static <T> T string2Obj(String str, TypeReference<T> typeReference) {
        if (StringUtils.isEmpty(str) || typeReference == null) {
            return null;
        }

        try {
            return (T) (typeReference.getType().equals(String.class) ? str : objectMapper.readValue(str, typeReference));
        } catch (IOException e) {
        	e.printStackTrace();
            return null;
        }
    }

    /**
     * 泛型反序列化
     * @param str
     * @param collectionClass 集合的类型
     * @param elementClasses 集合中元素的类型
     * @param <T>
     * @return
     */
    public static <T> T string2Obj(String str, Class<?> collectionClass, Class<?>... elementClasses) {
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
        try {
            return objectMapper.readValue(str, javaType);
        } catch (IOException e) {
        	e.printStackTrace();
            return null;
        }
    }
}

