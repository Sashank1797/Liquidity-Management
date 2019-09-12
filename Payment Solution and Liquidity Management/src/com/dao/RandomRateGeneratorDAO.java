
package com.dao;
import org.json.simple.JSONObject;
import java.util.List;

public interface RandomRateGeneratorDAO {

	public JSONObject populateRatesInDB();
	public  double getRandomDoubleBetweenRange(double min, double max);
	public List<Double> getRatesFromDB(String date);
}
