package top.chao.funding.manager.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.chao.funding.bean.TAdvertisement;
import top.chao.funding.manager.dao.TAdvertisementMapper;
import top.chao.funding.manager.service.AvertService;
import top.chao.funding.util.PageResult;
import top.chao.funding.util.StringUtil;
/**
 * @ClassName: AvertServiceImpl  
 * @Description: 广告相关操作接口实现 
 * @author: YiYChao
 * @date 2019年12月28日 下午7:20:30
 */
@Service
public class AvertServiceImpl implements AvertService{

	@Autowired
	private TAdvertisementMapper tAdvertisementMapper;
	
	@Override
	public PageResult<TAdvertisement> queryConditionPage(Map<String, Object> map) {
		PageResult<TAdvertisement> page = new PageResult<TAdvertisement>();
		Integer currentPage = (Integer) map.get("currentPage");
		Integer pageSizes = (Integer) map.get("pageSizes");
		String condition = (String) map.get("condition");
		
		page.setCurrentPage(currentPage);
		page.setPageSize(pageSizes);
		
		if(StringUtil.isEmpty(condition)) {
			condition = "";
		}else {
			condition = condition.replaceAll("%", "\\\\%");	// 防止查询条件中包含%，replaceAll为正在表达式消耗\ => \\%，Java消耗一个\=>\%
		}
		condition = "%" + condition + "%";
		
		int totalRecords = tAdvertisementMapper.queryConditionTotal(condition);
		page.setTotalRecords(totalRecords);
		List<TAdvertisement> list = tAdvertisementMapper.queryConditonList(page.getBegin(),page.getPageSize(),condition);
		page.setResultList(list);
		return page;
	}

	@Override
	public int saveAdvert(TAdvertisement advert) {
		return tAdvertisementMapper.insert(advert);
	}

	@Override
	public int deleteAdvertById(Integer id) {
		return tAdvertisementMapper.deleteAdvertById(id);
	}

}
