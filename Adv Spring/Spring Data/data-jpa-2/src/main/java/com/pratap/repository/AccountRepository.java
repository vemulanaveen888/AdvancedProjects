package com.pratap.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pratap.model.Account;
@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
	public List<Account>  findByName(String name);
	public Long deleteByName(String name);
	public Account getByNameAndBalance(String name , double balance);
	public List<Account>  queryByNameIsLike(String pattern);
	public List<Account>  readByBalanceLessThan(double balance);
	
	public List<Account>  findTop2ByBalanceGreaterThan(double min);
	
	@Query("Select acc from Account acc where  acc.accid= ?1")
	public Account getSingleAccount(Long id);
	
	@Query(value="select acc  from Account acc where acc.name= :name")
	public List<Account> getAccounts(@Param("name") String name);
	
	@Query(value="SELECT * FROM  ACCOUNT WHERE name= ?1 and balance=?2 ",nativeQuery=true)
	public List<Account>  getAccountsByNameAndBalance(String name , double balance);
	
	@Modifying
	@Query(value="Delete from Account where name=?1", nativeQuery=true)
	public int deleteRecordByName(String name);
}

