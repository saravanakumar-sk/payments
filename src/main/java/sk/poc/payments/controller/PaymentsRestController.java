package sk.poc.payments.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sk.poc.payments.dao.PaymentsDAO;
import sk.poc.payments.model.Payments;
import sk.poc.payments.service.PaymentsService;
import sk.poc.payments.util.CustomErrorType;

@RestController
@RequestMapping("/api")
public class PaymentsRestController {

	public static final Logger LOGGER = LoggerFactory.getLogger(PaymentsRestController.class);

	@Autowired
	PaymentsService paymentsService;

	@Autowired
	PaymentsDAO paymentsDAO;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/getPayments/{fromDate}/{toDate}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Payments>> getPaymentsResponse(
			@PathVariable @DateTimeFormat(iso = ISO.DATE) String fromDate,
			@PathVariable @DateTimeFormat(iso = ISO.DATE) String toDate,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size) {
		LOGGER.info("Entering getPayments API<<");
		LOGGER.info("From Date : " + fromDate + "|To Date : " + toDate + "|Page : " + page + "|Size : " + size);
		List<Payments> payments;
		try {
			if (null == page && null == size) {
				payments = paymentsService.getPayments(fromDate, toDate);
			} else if (page > 0 && size > 0) {
				payments = paymentsService.getPaymentsPaginated(fromDate, toDate, page, size);
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			LOGGER.error("Payments not found.");
			return new ResponseEntity(
					new CustomErrorType("Payments not found" + "Please try in the valid format."
							+ "http://localhost:8080/PaymentsRestAPI/api/getPayments/{fromDate}/{toDate}?page=<positive Integer>&size=<positive Integer>"
							+ "From date should be before to date"
							+ "Page and size are optional. But if page is available size is mandatory and vice versa. "
							+ "Page and size should be a number greater than zero."
							+ "Ex:localhost:8080/PaymentsRestAPI/api/getPayments/2000-01-01/2010-10-10?page=2&size=20 or "
							+ "Ex:localhost:8080/PaymentsRestAPI/api/getPayments/2000-01-01/2010-10-10"),
					HttpStatus.NOT_FOUND);
		}

		LOGGER.info("Exiting getPayments API>>");
		return new ResponseEntity<List<Payments>>(payments, HttpStatus.OK);
	}
}