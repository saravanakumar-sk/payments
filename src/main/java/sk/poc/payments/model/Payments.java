package sk.poc.payments.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Payments implements Serializable {

	private static final long serialVersionUID = 7927365746053341189L;
	
	private int id;
	private String beneName;
	private String beneAccount;
	private String creditorName;
	private String creditorAccount;
	private BigDecimal amount;
	private String date;
	
	public Payments(){
		
	}

	public Payments(int id, String beneName, String beneAccount, String creditorName, String creditorAccount,
			BigDecimal amount, String date) {
		super();
		this.id = id;
		this.beneName = beneName;
		this.beneAccount = beneAccount;
		this.creditorName = creditorName;
		this.creditorAccount = creditorAccount;
		this.amount = amount;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBeneName() {
		return beneName;
	}

	public void setBeneName(String beneName) {
		this.beneName = beneName;
	}

	public String getBeneAccount() {
		return beneAccount;
	}

	public void setBeneAccount(String beneAccount) {
		this.beneAccount = beneAccount;
	}

	public String getCreditorName() {
		return creditorName;
	}

	public void setCreditorName(String creditorName) {
		this.creditorName = creditorName;
	}

	public String getCreditorAccount() {
		return creditorAccount;
	}

	public void setCreditorAccount(String creditorAccount) {
		this.creditorAccount = creditorAccount;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
