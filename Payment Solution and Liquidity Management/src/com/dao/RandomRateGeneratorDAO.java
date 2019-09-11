package com.dao;

import org.json.simple.JSONObject;

public interface RandomRateGeneratorDAO {

	public JSONObject populateRatesInDB();
	public  JSONObject getRandomDoubleBetweenRange(double min, double max);
}