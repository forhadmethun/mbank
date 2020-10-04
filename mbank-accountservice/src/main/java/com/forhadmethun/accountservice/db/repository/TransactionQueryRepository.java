package com.forhadmethun.accountservice.db.repository;

/**
 * @author Md Forhad Hossain
 * @since 01/10/20
 */

import com.forhadmethun.accountservice.utility.dto.model.TransactionDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface TransactionQueryRepository {
    @Select("select * from transaction where account_id = #{accountId}")
    List<TransactionDto> findByAccountId(@Param("accountId") Long accountId);
}

