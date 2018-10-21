package sk.poc.payments.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sk.poc.payments.dao.PaymentsDAO;
import sk.poc.payments.model.Payments;

@Service("paymentsService")
public class PaymentsServiceImpl implements PaymentsService {

	@Autowired
	PaymentsDAO paymentsDAO;

	public static final Logger LOGGER = LoggerFactory.getLogger(PaymentsServiceImpl.class);

	private static List<Payments> dummyPayments;

	static {
		dummyPayments = populateDummyPayments();
	}

	@Override
	public List<Payments> getPayments(String fromDate, String toDate) throws Exception {
		LOGGER.info("Entering getPayments<<");
		try{
		List<Payments> payments = paymentsDAO.getPayments(fromDate, toDate);
		LOGGER.info("Exiting getPayments>>");
		return payments;
		}
		catch(Exception e){
			LOGGER.error("Exception in getting payments",e.getMessage());
			throw new Exception();
		}
	}

	@Override
	public List<Payments> getPaymentsPaginated(String fromDate, String toDate, int page, int size) throws Exception {
		LOGGER.info("Entering getPaymentsPaginated<<");
		try{
		List<Payments> payments = paymentsDAO.getPayments(fromDate, toDate);
		if (page * size > payments.size()) {
			return payments;
		}
		LOGGER.info(((page - 1) * size) + 1 + "," + (page) * size);
		LOGGER.info("Exiting getPaymentsPaginated>>");
		return payments.subList(((page - 1) * size), (page) * size);
		}
		catch(Exception e){
			LOGGER.error("Exception in getting payments",e.getMessage());
			throw new Exception();
		}
	}

	private static List<Payments> populateDummyPayments() {
		List<Payments> payments = new ArrayList<Payments>();
		payments.add(new Payments(1, "Saravana Kumar S K", "369", "Pramati Technologies", "123", new BigDecimal(125000),
				"2019-01-31"));
		payments.add(new Payments(2, "Saravana Kumar S K", "369", "Pramati Technologies", "123", new BigDecimal(125000),
				"2019-02-28"));
		payments.add(new Payments(3, "Saravana Kumar S K", "369", "Pramati Technologies", "123", new BigDecimal(125000),
				"2019-03-31"));
		payments.add(new Payments(4, "Saravana Kumar S K", "369", "Pramati Technologies", "123", new BigDecimal(125000),
				"2019-04-30"));
		return payments;
	}

}
