package sample.multimodule.accountQuery.config;

import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import sample.multimodule.accountModel.entity.AccountAggregate;

@Configuration
public class AccountConfig {
	@Bean
	EventSourcingRepository<AccountAggregate> accountAggregateEventSourcingRepository(EventStore eventStore) {
		return EventSourcingRepository.builder(AccountAggregate.class).eventStore(eventStore).build();
	}

}
