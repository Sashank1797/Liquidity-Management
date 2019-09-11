package com.dao;

public interface RandomRateGeneratorDAO {

	public boolean populateRatesInDB();
	public  double getRandomDoubleBetweenRange(double min, double max);
}