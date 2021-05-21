package sample.multimodule.accountCommand.service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import sample.multimodule.accountModel.command.CreateAccountCommand;
import sample.multimodule.accountModel.command.CreditMoneyCommand;
import sample.multimodule.accountModel.command.DebitMoneyCommand;
import sample.multimodule.accountModel.entity.AccountDto;
import sample.multimodule.accountModel.entity.MoneyCreditDto;
import sample.multimodule.accountModel.entity.MoneyDebitDto;

@Service
public class AccountCommandServiceImpl implements AccountCommandService {

	private CommandGateway commandGateway;

	public AccountCommandServiceImpl(CommandGateway commandGateway) {
		this.commandGateway = commandGateway;
	}

	@Override
	public CompletableFuture<String> createAccount(AccountDto accountDto) {
		return commandGateway.send(new CreateAccountCommand(UUID.randomUUID().toString(),
				accountDto.getAccountBalance(), accountDto.getCurrency()));
	}

	@Override
	public CompletableFuture<String> creditMoney(String accountNumber, MoneyCreditDto moneyCreditDto) {
		return commandGateway.send(
				new CreditMoneyCommand(accountNumber, moneyCreditDto.getCreditAmount(), moneyCreditDto.getCurrency()));
	}

	@Override
	public CompletableFuture<String> debitMoney(String accountNumber, MoneyDebitDto moneyDebitDto) {
		return commandGateway.send(
				new DebitMoneyCommand(accountNumber, moneyDebitDto.getDebitAmount(), moneyDebitDto.getCurrency()));
	}
}
