package com.anz.wholesale.accounts.repository;

import com.anz.wholesale.accounts.repository.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountsRepository extends JpaRepository<AccountEntity, String > {
    List<AccountEntity> findByCustomer_Id(String id);
}
