package com.oxchains.service;

import com.oxchains.mapper.UserMapper;
import com.oxchains.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * UserService
 * 
 * @author liuruichao
 * Created on 2016-01-15 10:18
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserService {
    @Resource
    private UserMapper userMapper;

    public List<User> list() {
        return userMapper.findAll();
    }

    public User getUser(Integer userId) {
        return userMapper.findById(userId);
    }
}
