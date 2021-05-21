package sample.multimodule.accountCommand.resource;

import java.util.concurrent.CompletableFuture;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sample.multimodule.accountCommand.service.AccountCommandService;
import sample.multimodule.accountModel.entity.AccountDto;
import sample.multimodule.accountModel.entity.MoneyCreditDto;
import sample.multimodule.accountModel.entity.MoneyDebitDto;

@RestController
@RequestMapping("/accounts")
public class AccountCommandResource {

	@Autowired
	public AccountCommandService accountCommandService;

	@PostMapping
	public CompletableFuture<String> createAccount(@Valid @RequestBody AccountDto accountDto) {
		return accountCommandService.createAccount(accountDto);
	}

	@PutMapping("/credits/{accountId}")
	public CompletableFuture<String> creditMoneytoAccount(
			@Pattern(regexp = "^[A-Za-z0-9-]+$", message = "Invalid account format") @PathVariable String accountId,
			@Valid @RequestBody MoneyCreditDto moneyCreditDto) {
		return accountCommandService.creditMoney(accountId, moneyCreditDto);
	}

	@PutMapping("/debits/{accountId}")
	public CompletableFuture<String> debitMoneyFromAccount(
			@Pattern(regexp = "^[A-Za-z0-9-]+$", message = "Invalid account format") @PathVariable String accountId,
			@Valid @RequestBody MoneyDebitDto moneyDebitDto) {
		return accountCommandService.debitMoney(accountId, moneyDebitDto);
	}
}
