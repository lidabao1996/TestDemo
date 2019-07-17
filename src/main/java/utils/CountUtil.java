package utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.regex.Pattern;

/**
 * 主要用于计算各种金额的  double类型
 * @author sophia
 * @since 2019-07-17
 */
public class CountUtil {
	// @Note("double加操作")
	public static double add(double d1, double d2) {
		BigDecimal c1 = new BigDecimal(d1);
		BigDecimal c2 = new BigDecimal(d2);
		BigDecimal result = c1.add(c2);
		return result.doubleValue();
	}

	// //@Note("累加")
	public static double add(double... ds) {
		BigDecimal c = new BigDecimal(0);
		for (double d : ds) {
			c = c.add(new BigDecimal(d));
		}
		return c.doubleValue();
	}

	// //@Note("double减操作")
	public static double subtract(double d1, double d2) {
		BigDecimal c1 = new BigDecimal(d1);
		BigDecimal c2 = new BigDecimal(d2);
		BigDecimal result = c1.subtract(c2);
		return result.doubleValue();
	}

	// //@Note("double除操作")
	public static double devide(double d1, double d2) {
		BigDecimal c1 = new BigDecimal(d1);
		BigDecimal c2 = new BigDecimal(d2);
		BigDecimal result = c1.divide(c2, 10, RoundingMode.HALF_DOWN);
		return result.doubleValue();
	}

	//@Note("double除操作")
	public static Double divide(Double dividend, Integer divisor, Integer scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(dividend));
		BigDecimal b2 = new BigDecimal(Double.toString(divisor));
		return b1.divide(b2, scale, RoundingMode.HALF_UP).doubleValue();
	}

	// //@Note("double乘操作")
	public static double multiply(double d1, double... arr) {

		BigDecimal result = new BigDecimal(d1);
		for (double d2 : arr) {
			result = result.multiply(new BigDecimal(d2));
		}
		return result.doubleValue();
	}

	// //@Note("四舍五入,例:112.355,2 >> 112.36 ,112.355,-1 >> 110")
	public static double round(double d1, int scale) {
		BigDecimal c1 = new BigDecimal(d1);
		BigDecimal result = c1.setScale(scale, RoundingMode.HALF_EVEN);
		return result.doubleValue();
	}

	// //@Note("四舍五入,保留2位小数")
	public static double round(double d1) {
		BigDecimal c1 = new BigDecimal(d1);
		BigDecimal result = c1.setScale(2, RoundingMode.HALF_EVEN);
		return result.doubleValue();
	}

	// @Note("四舍五入,保留6位小数")
	public static double roundSix(double d1) {
		BigDecimal c1 = new BigDecimal(d1);
		BigDecimal result = c1.setScale(6, RoundingMode.HALF_EVEN);
		return result.doubleValue();
	}

	public static double wipeTheZero(double num) {
		BigDecimal c1 = new BigDecimal(num);
		BigDecimal result = c1.setScale(0, BigDecimal.ROUND_DOWN);
		return result.doubleValue();
	}

	// @Note("保留相应的小数位数，但不做四舍五入操作。")
	public static double reservedDecimalPlaces(int digit, double num) {
		StringBuffer format = new StringBuffer();
		format.append(".");
		for (int i = 0; i < digit; i++) {
			format.append("0");
		}
		DecimalFormat df = new DecimalFormat(format.toString());
		df.setRoundingMode(RoundingMode.DOWN);
		return Double.valueOf(df.format(num)).doubleValue();
	}

	// @Note("保留相应的小数位数，但不做四舍五入操作。")
	public static double reservedDecimalHalfEvenPlaces(int digit, double num) {
		StringBuffer format = new StringBuffer();
		format.append(".");
		for (int i = 0; i < digit; i++) {
			format.append("0");
		}
		DecimalFormat df = new DecimalFormat(format.toString());
		df.setRoundingMode(RoundingMode.HALF_EVEN);
		return Double.valueOf(df.format(num)).doubleValue();
	}

	// @Note("判断字符串是不是整数")
	public static boolean isInteger(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
		return pattern.matcher(str).matches();
	}
}
