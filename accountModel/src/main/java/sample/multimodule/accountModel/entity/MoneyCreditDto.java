package sample.multimodule.accountModel.entity;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class MoneyCreditDto {
	@Min(value = 0L, message = "Credit amount can't be negative")
	private double creditAmount;
	@NotNull
	@Pattern(regexp = "[A-Za-z]{3}", message = " Invalid currency")
	private String currency;

	public double getCreditAmount() {
		return creditAmount;
	}

	public void setCreditAmount(double creditAmount) {
		this.creditAmount = creditAmount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Override
	public String toString() {
		return "MoneyCreditDto [creditAmount=" + creditAmount + ", currency=" + currency + "]";
	}

}
