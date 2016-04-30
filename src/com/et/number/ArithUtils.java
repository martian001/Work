package com.et.number;

import java.math.BigDecimal;

/**
 * 计算器功能类
 * @author： The One
 * @time：2014年3月27日 下午5:24:46
 * @version：1.0
 */
public class ArithUtils implements ArithUtilsInterface {
	/**
	 * 加法运算
	 */
	@Override
	public double add(double d1, double d2) throws ArithmeticException {
		BigDecimal result = BigDecimal.valueOf(d1).add(BigDecimal.valueOf(d2));
		return result.doubleValue();
	}

	/**
	 * 减法运算
	 */
	@Override
	public double sub(double d1, double d2) throws ArithmeticException {
		BigDecimal result = BigDecimal.valueOf(d1).subtract(BigDecimal.valueOf(d2));
		return result.doubleValue();
	}

	/**
	 * 乘法运算
	 */
	@Override
	public double mul(double d1, double d2) throws ArithmeticException {
		BigDecimal result = BigDecimal.valueOf(d1).multiply(BigDecimal.valueOf(d2));
		return result.doubleValue();
	}

	/**
	 * 除法运算
	 */
	@Override
	public double div(double d1, double d2) throws ArithmeticException {
		BigDecimal result = BigDecimal.valueOf(d1).divide(BigDecimal.valueOf(d2));
		return result.doubleValue();
	}

}
