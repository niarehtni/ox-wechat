package com.oxchains.service;

import com.oxchains.common.RespDTO;
import com.oxchains.mapper.TransactionMapper;
import com.oxchains.mapper.UserMapper;
import com.oxchains.model.Transaction;
import com.oxchains.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * TransactionService
 *
 * @author liuruichao
 * Created on 2016/12/19 10:30
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TransactionService {
    @Resource
    private TransactionMapper transactionMapper;

    @Resource
    private UserMapper userMapper;

    public RespDTO<Integer> create(Transaction transaction) {
        User user = userMapper.findById(transaction.getFromId());
        if (user.getAmount() < transaction.getMoney()) {
            return RespDTO.fail("余额不足!");
        }
        userMapper.plusAmount(transaction.getToId(), transaction.getMoney());
        userMapper.minusAmount(transaction.getFromId(), transaction.getMoney());
        Integer id = transactionMapper.save(transaction);
        return RespDTO.success("", id);
    }

    public List<Transaction> list(Integer userId) {
        List<Transaction> list = transactionMapper.list(userId);
        list = putUser(list);
        return list;
    }

    public List<Transaction> getAfterTransaction(Integer userId, Date date) {
        List<Transaction> list = transactionMapper.findAfterTime(userId, date);
        list = putUser(list);
        return list;
    }

    private List<Transaction> putUser(List<Transaction> list) {
        if (!list.isEmpty()) {
            for (Transaction transaction : list) {
                transaction.setFromUser(userMapper.findById(transaction.getFromId()));
                transaction.setToUser(userMapper.findById(transaction.getToId()));
            }
        }
        return list;
    }
}