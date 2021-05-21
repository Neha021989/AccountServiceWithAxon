package sample.multimodule.accountModel.entity;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import sample.multimodule.accountModel.command.CreateAccountCommand;
import sample.multimodule.accountModel.command.CreditMoneyCommand;
import sample.multimodule.accountModel.command.DebitMoneyCommand;
import sample.multimodule.accountModel.event.AccountActivatedEvent;
import sample.multimodule.accountModel.event.AccountCreatedEvent;
import sample.multimodule.accountModel.event.AccountHeldEvent;
import sample.multimodule.accountModel.event.MoneyCreditedEvent;
import sample.multimodule.accountModel.event.MoneyDebitedEvent;

@Aggregate
public class AccountAggregate {

	@AggregateIdentifier
	private String id;
	private double accountBalance;
	private String currency;
	private String status;

	public AccountAggregate() {
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

	@CommandHandler
	public AccountAggregate(CreateAccountCommand createAccountCommand) {
		AggregateLifecycle.apply(new AccountCreatedEvent(createAccountCommand.id, createAccountCommand.accountBalance,
				createAccountCommand.currency));
	}

	@EventSourcingHandler
	public void on(AccountCreatedEvent accountCreatedEvent) {
		this.id = accountCreatedEvent.id;
		this.accountBalance = accountCreatedEvent.accountBalance;
		this.currency = accountCreatedEvent.currency;
		this.status = String.valueOf(Status.CREATED);

		AggregateLifecycle.apply(new AccountActivatedEvent(this.id, Status.ACTIVE));
	}

	@EventSourcingHandler
	public void on(AccountActivatedEvent accountActivatedEvent) {
		this.status = String.valueOf(accountActivatedEvent.status);
	}

	@CommandHandler
	public void on(CreditMoneyCommand creditMoneyCommand) {
		AggregateLifecycle.apply(new MoneyCreditedEvent(creditMoneyCommand.id, creditMoneyCommand.creditAmount,
				creditMoneyCommand.currency));
	}

	@EventSourcingHandler
	public void on(MoneyCreditedEvent moneyCreditedEvent) {
		if (this.accountBalance < 0 && (this.accountBalance + moneyCreditedEvent.creditAmount) >= 0) {
			AggregateLifecycle.apply(new AccountActivatedEvent(this.id, Status.ACTIVE));
		}

		this.accountBalance += moneyCreditedEvent.creditAmount;
	}

	@CommandHandler
	public void on(DebitMoneyCommand debitMoneyCommand) {
		AggregateLifecycle.apply(
				new MoneyDebitedEvent(debitMoneyCommand.id, debitMoneyCommand.debitAmount, debitMoneyCommand.currency));
	}

	@EventSourcingHandler
	public void on(MoneyDebitedEvent moneyDebitedEvent) {
		if (this.accountBalance >= 0 && (this.accountBalance - moneyDebitedEvent.debitAmount) < 0) {
			AggregateLifecycle.apply(new AccountHeldEvent(this.id, Status.INACTIVE));
		}
		this.accountBalance -= moneyDebitedEvent.debitAmount;
	}

	@EventSourcingHandler
	public void on(AccountHeldEvent accountHeldEvent) {
		this.status = String.valueOf(accountHeldEvent.status);
	}

}
