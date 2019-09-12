package com.dao;

import java.util.List;

import org.json.simple.JSONObject;

public interface NotionalPoolingDAO {

public JSONObject poolingFunction();
public List<Double> calculateTotalBalance_from_TRANSACTIONS_table(String accountNo_usd,String accountNo_eur,String accountNo_gbp, String date);
public List<Double> getBalance_from_ACCOUNTS_table(String currency1,String currency2,String currency3);
public List<Double> calculateNetBalance();
}
