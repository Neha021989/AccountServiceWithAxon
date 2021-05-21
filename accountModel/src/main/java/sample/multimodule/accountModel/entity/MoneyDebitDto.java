package sample.multimodule.accountModel.entity;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class MoneyDebitDto {
	@Min(value = 0L, message = "Debit amount can't be negative")
	private double debitAmount;
	@NotNull
	@Pattern(regexp = "[A-Za-z]{3}", message = " Invalid currency")
	private String currency;

	public double getDebitAmount() {
		return debitAmount;
	}

	public void setDebitAmount(double debitAmount) {
		this.debitAmount = debitAmount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Override
	public String toString() {
		return "MoneyCreditDto [debitAmount=" + debitAmount + ", currency=" + currency + "]";
	}

}
