package com.forhadmethun.accountservice.db.repository;

/**
 * @author Md Forhad Hossain
 * @since 01/10/20
 */

import com.forhadmethun.accountservice.db.entity.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountCommandRepository extends CrudRepository<Account, Long> {

}
