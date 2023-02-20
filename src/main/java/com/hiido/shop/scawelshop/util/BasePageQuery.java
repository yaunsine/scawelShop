package com.hiido.shop.scawelshop.util;

import lombok.Getter;

/**
 * @author pzw
 * @date 2021/8/31 17:48
 */
public class BasePageQuery {
	/**
	 * 页码, pageNo和pageSize都传零，表示查全部
	 */
	@Getter
	private int pageNo;

	/**
	 * 每页数据量
	 */
	@Getter
	private int pageSize;

	public void setPageNo(int pageNo) {
		this.pageNo = Math.max(1, pageNo);
	}

	public void setPageSize(int pageSize) {
		this.pageSize = Math.max(0, pageSize);
	}

	public int getLimit() {
		return pageSize;
	}

	public int getOffset() {
		return (pageNo - 1) * pageSize;
	}
}
