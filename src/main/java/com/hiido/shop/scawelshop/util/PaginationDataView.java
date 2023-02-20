package com.hiido.shop.scawelshop.util;

import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author pzw
 * @date 2021/8/31 18:06
 */
@Data
public class PaginationDataView<T> {
	/**
	 * 当前页数据
	 */
	private List<T> list;

	/**
	 * 数据总量
	 */
	private int total;

	/**
	 * 页码
	 */
	private int pageNo;

	/**
	 * 每页数据量
	 */
	private int pageSize;

	/**
	 * 页数
	 */
	private int pages;

	public static <T> PaginationDataView<T> build(PageInfo<T> pageInfo) {
		PaginationDataView<T> p = new PaginationDataView<>();
		p.list = pageInfo.getList();
		p.total = (int) pageInfo.getTotal();
		p.pageNo = pageInfo.getPageNum();
		p.pageSize = pageInfo.getPageSize();
		p.pages = pageInfo.getPages();
		return p;
	}

	public static <T, R> PaginationDataView<R> build(PageInfo<T> pageInfo, Function<T, R> convertFunction) {
		PaginationDataView<R> p = new PaginationDataView<>();
		p.list = pageInfo.getList().stream().map(convertFunction).collect(Collectors.toList());
		p.total = (int) pageInfo.getTotal();
		p.pageNo = pageInfo.getPageNum();
		p.pageSize = pageInfo.getPageSize();
		p.pages = pageInfo.getPages();
		return p;
	}
}
