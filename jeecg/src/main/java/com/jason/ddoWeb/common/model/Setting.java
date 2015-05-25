/**
 * 
 */
package com.jason.ddoWeb.common.model;

/**
 * 系统配置
 * @author jasonzhang
 *
 */
public class Setting {

	//每月扣费金额限制
	private double monthDeductionAmountLimit;
	//每月扣费次数限制
	private int monthDeductionNumLimit;
	public double getMonthDeductionAmountLimit() {
		return monthDeductionAmountLimit;
	}
	public void setMonthDeductionAmountLimit(double monthDeductionAmountLimit) {
		this.monthDeductionAmountLimit = monthDeductionAmountLimit;
	}
	public int getMonthDeductionNumLimit() {
		return monthDeductionNumLimit;
	}
	public void setMonthDeductionNumLimit(int monthDeductionNumLimit) {
		this.monthDeductionNumLimit = monthDeductionNumLimit;
	}
}
