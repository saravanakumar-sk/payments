package sk.poc.payments.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import sk.poc.payments.model.Payments;
import sk.poc.payments.util.DateUtil;

@Service("paymentsDAO")
public class PaymentsDAO {

	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentsDAO.class);
	private static boolean isDebugEnabled = LOGGER.isDebugEnabled();
	
	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;
	@Autowired
	private DateUtil dateUtil;

	public List<Payments> getPayments(String fromDate, String toDate) throws Exception {
		LOGGER.info("Entering getPayments>>");
		LOGGER.info("From Date : "+fromDate+"|To Date : "+toDate);
		try {
			Map<String, Date> paramsMap = new HashMap<String, Date>();
			paramsMap.put("fromDate", dateUtil.parseDate(fromDate));
			paramsMap.put("toDate", dateUtil.parseDate(toDate));
			if(dateUtil.parseDate(fromDate).after(dateUtil.parseDate(toDate))){
				throw new Exception();
			}
			
			if(isDebugEnabled){
				LOGGER.debug("SQL for getPayments is : "+SQLConstants.getPayments);
			}
			
			List<Payments> payments = new ArrayList<Payments>();
			List<Map<String, Object>> rows = namedJdbcTemplate.queryForList(SQLConstants.getPayments, paramsMap);

			for (Map<String, Object> row : rows) {
				Payments payment = new Payments();
				payment.setId((int) row.get("ID"));
				payment.setBeneName((String) row.get("BENE_NAME"));
				payment.setBeneAccount((String) row.get("BENE_ACCOUNT"));
				payment.setCreditorName((String) row.get("CREDITOR_NAME"));
				payment.setCreditorAccount((String) row.get("CREDITOR_ACCOUNT"));
				payment.setAmount(new BigDecimal(row.get("AMOUNT").toString()));
				payment.setDate(row.get("DATE").toString());

				payments.add(payment);
			}
			
			if(isDebugEnabled){
				LOGGER.debug("Payments fetched : "+payments);
			}
			
			LOGGER.info("Exiting getPayments<<");
			return payments;
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Error in getPayments",e.getMessage());
			throw new Exception();
		}
	}
	
	/*public List<Payments> getPayments(String fromDate, String toDate, int page, int size) {
		System.out.println("From Date :::::::::::::::::::::::::" + fromDate);
		System.out.println("To Date :::::::::::::::::::::::::" + toDate);
		try {
			Map<String, Object> paramsMap = new HashMap<String, Object>();
			paramsMap.put("fromDate", dateUtil.parseDate(fromDate));
			paramsMap.put("toDate", dateUtil.parseDate(toDate));
			paramsMap.put("page", page-1);
			paramsMap.put("size", size);
			
			List<Payments> payments = new ArrayList<Payments>();
			List<Map<String, Object>> rows = namedJdbcTemplate.queryForList(SQLConstants.getPaymentsWithPage, paramsMap);

			for (Map<String, Object> row : rows) {
				Payments payment = new Payments();
				payment.setId((int) row.get("ID"));
				payment.setBeneName((String) row.get("BENE_NAME"));
				payment.setBeneAccount((String) row.get("BENE_ACCOUNT"));
				payment.setCreditorName((String) row.get("CREDITOR_NAME"));
				payment.setCreditorAccount((String) row.get("CREDITOR_ACCOUNT"));
				payment.setAmount(new BigDecimal(row.get("AMOUNT").toString()));
				payment.setDate(row.get("DATE").toString());

				payments.add(payment);
			}
			return payments;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}*/
}
