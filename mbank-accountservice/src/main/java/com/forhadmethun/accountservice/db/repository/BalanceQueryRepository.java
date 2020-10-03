package com.forhadmethun.accountservice.db.repository;

/**
 * @author Md Forhad Hossain
 * @since 01/10/20
 */

import com.forhadmethun.accountservice.db.entity.Balance;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BalanceQueryRepository {
    @Select("select * from balance where account_id = #{accountId}")
    List<Balance> findByAccountId(@Param("accountId") Long accountId);

    @Select("select * from balance where account_id = #{accountId} and currency = #{currency}")
    Balance findByAccountIdAndCurrency(@Param("accountId") Long accountId, @Param("currency")  String currency);
}
