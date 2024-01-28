package com.pratap.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pratap.model.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

}
