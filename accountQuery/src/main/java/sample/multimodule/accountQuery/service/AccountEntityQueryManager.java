package sample.multimodule.accountQuery.service;

import java.util.Optional;

import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import sample.multimodule.accountCommand.model.AccountAggregate;
import sample.multimodule.accountModel.event.BaseEvent;
import sample.multimodule.accountQuery.entity.AccountQueryEntity;
import sample.multimodule.accountQuery.repository.AccountQueryRepository;

@Component
public class AccountEntityQueryManager {

	@Autowired
	private AccountQueryRepository accountQueryRepository;

	@Autowired
	@Qualifier("accountAggregateEventSourcingRepository")
	private EventSourcingRepository<AccountAggregate> accountAggregateEventSourcingRepository;

	@EventSourcingHandler
	void on(BaseEvent baseEvent) {
		persistAccount(buildQueryAccount(getAccountFromEvent(baseEvent)));
	}

	private AccountAggregate getAccountFromEvent(BaseEvent baseEvent) {
		return accountAggregateEventSourcingRepository.load(baseEvent.id.toString()).getWrappedAggregate()
				.getAggregateRoot();
	}

	private AccountQueryEntity buildQueryAccount(AccountAggregate accountAggregate) {
		Optional<AccountQueryEntity> accountEntity = accountQueryRepository.findById(accountAggregate.getId());
		AccountQueryEntity accountQueryEntity = accountEntity.isPresent() ? accountEntity.get()
				: new AccountQueryEntity();
		accountQueryEntity.setId(accountAggregate.getId());
		accountQueryEntity.setAccountBalance(accountAggregate.getAccountBalance());
		accountQueryEntity.setCurrency(accountAggregate.getCurrency());
		accountQueryEntity.setStatus(accountAggregate.getStatus());
		return accountQueryEntity;
	}

	private void persistAccount(AccountQueryEntity accountQueryEntity) {
		accountQueryRepository.save(accountQueryEntity);
	}

}
