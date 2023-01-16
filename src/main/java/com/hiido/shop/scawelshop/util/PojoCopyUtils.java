package com.hiido.shop.scawelshop.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author pzw
 * @date 2020/11/26 15:10
 * @deprecated
 */
public class PojoCopyUtils {
	public static final ObjectMapper OM = new ObjectMapper();

	private PojoCopyUtils() {
	}



	public static <S, T> T copyProperties(S source, Supplier<T> target) {
		if (source == null) {
			return null;
		}
		T t = target.get();
		BeanUtils.copyProperties(source, t);
		return t;
	}

	public static <S, T> List<T> copyListProperties(List<S> sources, Supplier<T> target) {
		List<T> list = new ArrayList<>(sources.size());
		for (S source : sources) {
			T t = target.get();
			BeanUtils.copyProperties(source, t);
			list.add(t);
		}
		return list;
	}

	public static String toJsonString(Object object) {
		try {
			return OM.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			throw new RuntimeException("结果转换json异常", e);
		}
	}

}
