package sample.multimodule.accountQuery.service;

import java.util.List;

import sample.multimodule.accountQuery.entity.AccountQueryEntity;

public interface AccountQueryService {

	public List<Object> listAllEventsForAccount(String accountId);

	public AccountQueryEntity getAccount(String accountId);

}
