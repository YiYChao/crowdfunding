package top.chao.funding.util;

import java.util.List;
/**
 * @ClassName: PageResult  
 * @Description: 分页查询结果集对象 
 * @author: YiYChao
 * @date 2019年12月18日 下午10:39:49
 */
public class PageResult<E> {

	private Integer pageSize;		// 页大小
	private Integer currentPage;	// 当前页
	private Integer totalRecords;	// 总记录数
	private Integer totalPages;		// 总页数
	private List<E> resultList;		// 分页结果集	
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		if(pageSize <= 0) {
			pageSize = 10;
		}
		this.pageSize = pageSize;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		if(currentPage <= 0) {
			currentPage = 1;
		}
		this.currentPage = currentPage;
	}
	public Integer getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(Integer totalRecords) {
		this.totalRecords = totalRecords;
		// 计算并设置总页数
		this.totalPages = (totalRecords % this.pageSize == 0) ? (totalRecords / this.pageSize) : (totalRecords / this.pageSize + 1);
	}
	public Integer getTotalPages() {
		return totalPages;
	}
	@SuppressWarnings("unused")
	private void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}
	public List<E> getResultList() {
		return resultList;
	}
	public void setResultList(List<E> resultList) {
		this.resultList = resultList;
	}
	
	public Integer getBegin() {
		if(this.currentPage <= 0) {
			this.currentPage = 1;
		}
		if(this.pageSize <= 0) {
			this.pageSize = 10;
		}
		return (this.currentPage - 1) * this.pageSize;
	}
	
}
