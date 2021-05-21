package sample.multimodule.accountQuery.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.stereotype.Service;

import sample.multimodule.accountQuery.entity.AccountQueryEntity;
import sample.multimodule.accountQuery.repository.AccountQueryRepository;

@Service
public class AccountQueryServiceImpl implements AccountQueryService {

	private final EventStore eventStore;
	private final AccountQueryRepository accountQueryRepository;

	public AccountQueryServiceImpl(EventStore eventStore, AccountQueryRepository accountQueryRepository) {
		this.eventStore = eventStore;
		this.accountQueryRepository = accountQueryRepository;
	}

	@Override
	public List<Object> listAllEventsForAccount(String accountId) {
		return eventStore.readEvents(accountId).asStream().map(s -> s.getPayload()).collect(Collectors.toList());
	}

	@Override
	public AccountQueryEntity getAccount(String accountId) {
		Optional<AccountQueryEntity> accountQueryEntity = accountQueryRepository.findById(accountId);
		return accountQueryEntity.isPresent() ? accountQueryEntity.get() : null;
	}

}
