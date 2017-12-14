package yjh.entity;

import com.sun.rowset.CachedRowSetImpl;

public class Goods {
	CachedRowSetImpl rowSetImpl = null;	//表格中的行集对象
	private int pageSize = 5; //每页多少条记录
	private int currentPage = 1;	//当前页码
	private int totalRecord = 1;	//总记录数
	private int totalPage = 1;		//总页数
	
	public Goods() {}

	public Goods(CachedRowSetImpl rowSetImpl, int pageSize, int currentPage, int totalRecord, int totalPage) {
		super();
		this.rowSetImpl = rowSetImpl;
		this.pageSize = pageSize;
		this.currentPage = currentPage;
		this.totalRecord = totalRecord;
		this.totalPage = totalPage;
	}

	public CachedRowSetImpl getRowSetImpl() {
		return rowSetImpl;
	}

	public void setRowSetImpl(CachedRowSetImpl rowSetImpl) {
		this.rowSetImpl = rowSetImpl;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	@Override
	public String toString() {
		return "Goods [rowSetImpl=" + rowSetImpl + ", pageSize=" + pageSize + ", currentPage=" + currentPage
				+ ", totalRecord=" + totalRecord + ", totalPage=" + totalPage + "]";
	}
	
	public static void main(String[] args) {
	}
}
