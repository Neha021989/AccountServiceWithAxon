package sample.multimodule.accountQuery.resource;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sample.multimodule.accountQuery.entity.AccountQueryEntity;
import sample.multimodule.accountQuery.service.AccountQueryService;

@RestController
@RequestMapping("/accounts")
public class AccountQueryResource {

	private final AccountQueryService accountQueryService;

	public AccountQueryResource(AccountQueryService accountQueryService) {
		this.accountQueryService = accountQueryService;
	}

	@GetMapping("/events/{accountId}")
	public List<Object> getEventsForAccount(@PathVariable String accountId) {
		return accountQueryService.listAllEventsForAccount(accountId);
	}

	@GetMapping("{accountId}")
	public AccountQueryEntity getAccountById(@PathVariable String accountId) {
		return accountQueryService.getAccount(accountId);
	}

}
