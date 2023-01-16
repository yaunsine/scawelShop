package com.hiido.shop.scawelshop.util;

import java.math.BigDecimal;
import java.math.RoundingMode;


/**
 * @description: TODO 小数转换工具类
 * @author YSLin
 * @date 2023/1/13 11:18
 */
public class DecimalNumberConvertUtils {
    /**
     * double小数保留，四舍五入
     * @param number 需要转换的数字
     * @param n 保留多少位小数
     * @return 返回转换后的结果
     */
    public static double getNumber(double number, int n) {
        BigDecimal bigDecimal = new BigDecimal(number);
        return bigDecimal.setScale(n, RoundingMode.HALF_UP).doubleValue();
    }
}
