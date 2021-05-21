package sample.multimodule.accountQuery.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sample.multimodule.accountQuery.entity.AccountQueryEntity;

public interface AccountQueryRepository extends JpaRepository<AccountQueryEntity, String> {

}
