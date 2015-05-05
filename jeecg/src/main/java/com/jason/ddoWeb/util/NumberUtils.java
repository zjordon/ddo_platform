/**
 * 
 */
package com.jason.ddoWeb.util;

/**
 * @author jasonzhang
 *
 */
public class NumberUtils {

	public static Long doubleToLong(Double doulbeValue) {
		Long longValue;
		if (doulbeValue != null) {
			longValue = new Long(Math.round(doulbeValue.doubleValue() * 100));
		} else {
			longValue = new Long(0L);
		}
		return longValue;
	}
	
	public static Double intToDouble(Integer intValue) {
		return intValue != null ? new Double(((double)(intValue.intValue()))/100) : new Double(0.0);
	}
	
	public static Integer doubleToInt(Double doulbeValue) {
		Integer intValue;
		if (doulbeValue != null) {
			intValue = new Integer((int)Math.round(doulbeValue.doubleValue() * 100));
		} else {
			intValue = new Integer(0);
		}
		return intValue;
	}
	
	public static Double longToDouble(Long longValue) {
		return longValue != null ? new Double(((double)(longValue.longValue()))/100) : new Double(0.0);
	}
	
	public final static void main(String[] args) {
		Double testDouble = new Double(11.21);
		Long testLong = doubleToLong(testDouble);
		System.out.println(testLong);
		testLong = new Long(10101121L);
		testDouble = longToDouble(testLong);
		System.out.println(testDouble);
		Integer testInt = new Integer(101);
		testDouble = intToDouble(testInt);
		System.out.println(testDouble);
		testDouble = new Double(11.2);
		testInt = doubleToInt(testDouble);
		System.out.println(testInt);
	}
}
