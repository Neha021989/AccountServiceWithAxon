package sample.multimodule.accountQuery.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AccountQueryEntity {

	@Id
	private String id;
	private double accountBalance;
	private String currency;
	private String status;

	public AccountQueryEntity() {

	}

	public AccountQueryEntity(String id, double accountBalance, String currency, String status) {
		super();
		this.id = id;
		this.accountBalance = accountBalance;
		this.currency = currency;
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "AccountQueryEntity [id=" + id + ", accountBalance=" + accountBalance + ", currency=" + currency
				+ ", status=" + status + "]";
	}

}
