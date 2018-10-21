package sk.poc.payments.service;


import java.util.List;

import sk.poc.payments.model.Payments;

public interface PaymentsService {
	
	public List<Payments> getPayments(String fromDate, String toDate) throws Exception;
	
	public List<Payments> getPaymentsPaginated(String fromDate, String toDate, int page, int size) throws Exception;
	
}
