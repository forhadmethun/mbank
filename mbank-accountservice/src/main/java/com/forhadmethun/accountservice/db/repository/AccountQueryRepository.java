package com.forhadmethun.accountservice.db.repository;

import com.forhadmethun.accountservice.utility.dto.model.AccountDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AccountQueryRepository {
    @Select("select account_id, customer_id from account where account_id = #{accountId}")
    AccountDto findByAccountId(@Param("accountId") Long accountId);
}
