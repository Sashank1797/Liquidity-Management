import com.dao.UserTransactionDetailsDAO;
import com.logic.UserTransactionDetailsDAOImpl;

public class TestAddTransaction {
	
	
	public static void main(String[] args) {
		
		UserTransactionDetailsDAO userTran=new UserTransactionDetailsDAOImpl();
		userTran.addTransaction("243567","7010067259", "7010067576", 
				new java.sql.Date(System.currentTimeMillis()), 37546.78, "USD");
		userTran.addTransaction("243567","7010048398", "7010067576", 
				new java.sql.Date(System.currentTimeMillis()), 37546.78, "EUR");
		userTran.addTransaction("243567","7010023465", "7010067576", 
				new java.sql.Date(System.currentTimeMillis()), 37546.78, "GBP");
		
	}

}
