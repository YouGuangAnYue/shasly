package com.shasly.common.bean;

import java.util.List;

public class PageBean<T> {

	private long totalCount ;	//总数据个数
	private int pageNum ;	//当前页
	private int pageSize ;	//每页数据数
	private int startPage ; //开始页
	private int endPage ;	//结束页
	private int totalPage ;
	
	private List<T> data ;
	
	public PageBean() {
		// TODO Auto-generated constructor stub
	}

	public PageBean(long totalCount, int pageNum, int pageSize) {
		this.totalCount = totalCount;
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		
		//计算总页数
		int page = (int) (totalCount/pageSize) ;
		this.totalPage = totalCount%pageSize==0 ? page : page+1 ;
		//计算开始和结束页
		if (totalPage <= 10) {
			startPage = 1 ;
			endPage = totalPage ;
		}else if (pageNum - 5 <1) {
			startPage = 1 ;
			endPage = 10 ;
		}else if (pageNum + 4 >= totalPage) {
			endPage = totalPage ;
			startPage = endPage - 9 ;
		}else {
			startPage = pageNum - 5 ;
			endPage = pageNum + 4 ;
		}
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "PageBean [totalCount=" + totalCount + ", pageNum=" + pageNum + ", pageSize=" + pageSize + ", startPage="
				+ startPage + ", endPage=" + endPage + ", totalPage=" + totalPage + ", data=" + data + "]";
	}

	
	
}
