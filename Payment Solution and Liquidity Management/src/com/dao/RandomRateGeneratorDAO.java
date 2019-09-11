package com.dao;

import java.util.List;

public interface RandomRateGeneratorDAO {

	public boolean populateRatesInDB();
	public  double getRandomDoubleBetweenRange(double min, double max);
	public List<Double> getRatesFromDB(String date);
	
}