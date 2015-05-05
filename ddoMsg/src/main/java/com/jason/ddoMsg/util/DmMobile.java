package com.jason.ddoMsg.util;

public class DmMobile {

	public DmMobile(){}
	public DmMobile(int msisdn, int provinceCode, int cityCode) {
		this.msisdn = msisdn;
		this.provinceCode = provinceCode;
		this.cityCode = cityCode;
	}
	public int msisdn;
	public int provinceCode;
	public int cityCode;
}
