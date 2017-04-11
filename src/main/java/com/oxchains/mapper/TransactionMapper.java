package com.oxchains.mapper;

import com.oxchains.model.Transaction;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * TransactionMapper
 *
 * @author liuruichao
 * Created on 2016/12/19 10:30
 */
@Repository
public interface TransactionMapper {
    Integer save(Transaction transaction);

    List<Transaction> findAll();

    List<Transaction> list(Integer userId);

    List<Transaction> findAfterTime(@Param("userId") Integer userId, @Param("date") Date date);
}
