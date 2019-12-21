package top.chao.funding.vo;
/**
 * @ClassName: DataVO  
 * @Description: 虚拟的数据实体，用于接收前端的多条数据记录 
 * @author: YiYChao
 * @date 2019年12月21日 下午2:11:19
 */

import java.util.List;

public class DataVO {

	private List<Integer> ids;

	public List<Integer> getIds() {
		return ids;
	}

	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}
	
}
