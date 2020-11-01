package com.anz.wholesale.customers.repository;

import com.anz.wholesale.customers.repository.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CustomersRepository extends JpaRepository<CustomerEntity,String> {
    @Override
    Optional<CustomerEntity> findById(String id);
}
