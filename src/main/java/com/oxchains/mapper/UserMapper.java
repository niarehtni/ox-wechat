package com.oxchains.mapper;

import com.oxchains.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UserMapper
 *
 * @author liuruichao
 * Created on 2016-01-15 10:30
 */
@Repository
public interface UserMapper {
    int save(User user);

    List<User> findAll();

    User findById(Integer userId);

    void plusAmount(@Param("id") Integer id, @Param("money") Double money);

    void minusAmount(@Param("id") Integer id, @Param("money") Double money);
}