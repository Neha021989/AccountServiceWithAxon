package sample.multimodule.accountModel.entity;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class AccountDto {
	@Min(value = 0L, message = "Opening Balance can't be negative")
	private double accountBalance;
	@NotNull
	@Pattern(regexp = "[A-Za-z]{3}", message = " Invalid currency")
	private String currency;

	public AccountDto() {

	}

	public AccountDto(double accountBalance, String currency) {
		this.accountBalance = accountBalance;
		this.currency = currency;
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

	@Override
	public String toString() {
		return "AccountDto [accountBalance=" + accountBalance + ", currency=" + currency + "]";
	}

}
