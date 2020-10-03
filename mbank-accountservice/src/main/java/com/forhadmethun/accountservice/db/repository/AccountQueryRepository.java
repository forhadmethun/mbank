package com.forhadmethun.accountservice.db.repository;

import com.forhadmethun.accountservice.db.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AccountQueryRepository {
    @Select("select account_id, customer_id from account where account_id = #{accountId}")
    Account findByAccountId(@Param("accountId") Long accountId);
}
