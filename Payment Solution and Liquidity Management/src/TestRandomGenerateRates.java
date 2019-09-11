import com.dao.RandomRateGeneratorDAO;
import com.logic.RandomRateGeneratorDAOImpl;

public class TestRandomGenerateRates {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RandomRateGeneratorDAO randomRate=new RandomRateGeneratorDAOImpl();
		randomRate.populateRatesInDB();
	}

}
