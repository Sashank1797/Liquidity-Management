
package com.dao;
import java.sql.Date;
import java.util.List;
import org.json.simple.JSONObject;
import javax.print.DocFlavor.STRING;

import com.pojo.UserTransactionDetails;


public interface UserTransactionDetailsDAO {

public JSONObjec addTransaction(String transactionID,String userAccountNo, String counterpartyAccountNo, String date,double amount,String currency);
public JSONObjec getTransactionbyAccount(String AccountNo);
public void randomGenerateCashflow();
public JSONObjec deleteTransaction(String transactionID);

}

}

