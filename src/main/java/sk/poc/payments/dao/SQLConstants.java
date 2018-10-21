package sk.poc.payments.dao;

public class SQLConstants {

	public static final String getPayments = "Select * from PaymentsPOC.payments where date between :fromDate and :toDate";
	public static final String getPaymentsWithPage = "Select * from sk.payments where date between :fromDate and :toDate limit :page, :size";
}
