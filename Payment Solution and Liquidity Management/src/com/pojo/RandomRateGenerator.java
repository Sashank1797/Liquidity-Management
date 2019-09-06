package com.pojo;

public class RandomRateGenerator {
	
	float USDdebit;
	float USDcredit;
	float GBPdebit;
	float GBPcredit;
	float EURdebit;
	float EURcredit;
	float USDGBPbid;
	float USDGBPask;
	float USDEURbid;
	float USDEURask;
	public float getUSDdebit() {
		return USDdebit;
	}
	public void setUSDdebit(float uSDdebit) {
		USDdebit = uSDdebit;
	}
	public float getUSDcredit() {
		return USDcredit;
	}
	public void setUSDcredit(float uSDcredit) {
		USDcredit = uSDcredit;
	}
	public float getGBPdebit() {
		return GBPdebit;
	}
	public void setGBPdebit(float gBPdebit) {
		GBPdebit = gBPdebit;
	}
	public float getGBPcredit() {
		return GBPcredit;
	}
	public void setGBPcredit(float gBPcredit) {
		GBPcredit = gBPcredit;
	}
	public float getEURdebit() {
		return EURdebit;
	}
	public void setEURdebit(float eURdebit) {
		EURdebit = eURdebit;
	}
	public float getEURcredit() {
		return EURcredit;
	}
	public void setEURcredit(float eURcredit) {
		EURcredit = eURcredit;
	}
	public float getUSDGBPbid() {
		return USDGBPbid;
	}
	public void setUSDGBPbid(float uSDGBPbid) {
		USDGBPbid = uSDGBPbid;
	}
	public float getUSDGBPask() {
		return USDGBPask;
	}
	public void setUSDGBPask(float uSDGBPask) {
		USDGBPask = uSDGBPask;
	}
	public float getUSDEURbid() {
		return USDEURbid;
	}
	public void setUSDEURbid(float uSDEURbid) {
		USDEURbid = uSDEURbid;
	}
	public float getUSDEURask() {
		return USDEURask;
	}
	public void setUSDEURask(float uSDEURask) {
		USDEURask = uSDEURask;
	}
	public RandomRateGenerator(float uSDdebit, float uSDcredit, float gBPdebit, float gBPcredit, float eURdebit,
			float eURcredit, float uSDGBPbid, float uSDGBPask, float uSDEURbid, float uSDEURask) {
		super();
		USDdebit = uSDdebit;
		USDcredit = uSDcredit;
		GBPdebit = gBPdebit;
		GBPcredit = gBPcredit;
		EURdebit = eURdebit;
		EURcredit = eURcredit;
		USDGBPbid = uSDGBPbid;
		USDGBPask = uSDGBPask;
		USDEURbid = uSDEURbid;
		USDEURask = uSDEURask;
	}
	public RandomRateGenerator() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
