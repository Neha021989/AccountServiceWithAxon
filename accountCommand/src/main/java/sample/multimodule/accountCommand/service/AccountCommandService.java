package sample.multimodule.accountCommand.service;

import java.util.concurrent.CompletableFuture;

import sample.multimodule.accountModel.entity.AccountDto;
import sample.multimodule.accountModel.entity.MoneyCreditDto;
import sample.multimodule.accountModel.entity.MoneyDebitDto;

public interface AccountCommandService {

	public CompletableFuture<String> createAccount(AccountDto accountDto);

	public CompletableFuture<String> creditMoney(String accountNumber, MoneyCreditDto moneyCreditDto);

	public CompletableFuture<String> debitMoney(String accountNumber, MoneyDebitDto moneyDebitDto);

}
