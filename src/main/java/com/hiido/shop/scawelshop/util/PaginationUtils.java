package com.hiido.shop.scawelshop.util;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author pzw
 * @date 2021/8/31 18:05
 */
public class PaginationUtils {

	public static <T, R> PaginationDataView<R> wrapPaginationData(
			BasePageQuery pageQuery,
			Supplier<List<T>> listDataSupplier,
			Function<T, R> convertFunction) {
		PageHelper.offsetPage(pageQuery.getOffset(), pageQuery.getLimit());
		List<T> data = listDataSupplier.get();
		PageInfo<T> pageInfo = new PageInfo<>(data);
		return PaginationDataView.build(pageInfo, convertFunction);
	}

	public static <T> PaginationDataView<T> wrapPaginationData(
			BasePageQuery pageQuery,
			Supplier<List<T>> listDataSupplier) {
		PageHelper.offsetPage(pageQuery.getOffset(), pageQuery.getLimit());
		List<T> data = listDataSupplier.get();
		PageInfo<T> pageInfo = new PageInfo<>(data);
		return PaginationDataView.build(pageInfo);
	}
}
