import com.dao.UserLoginDAO;
import com.dao.UserTransactionDetailsDAO;
import com.logic.UserLoginDAOImpl;
import com.logic.UserTransactionDetailsDAOImpl;
import com.pojo.UserTransactionDetails;

public class Test {

	public static void main(String[] args) {
		UserLoginDAO dao = new UserLoginDAOImpl();
		dao.authenticateUser("raghav", "pass");
		dao.authenticateUser("raghav", "fail");
		UserTransactionDetailsDAO dao2 = new UserTransactionDetailsDAOImpl();
		dao2.getTransactionbyAccount("342847847");
//		dao2.getTransactionbyAccount(1111);
	}
}
