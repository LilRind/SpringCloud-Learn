package com.itheima.mp.mapper;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.itheima.mp.domain.po.User;
import org.apache.ibatis.annotations.Param;
import org.yaml.snakeyaml.scanner.Constant;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

//    void saveUser(User user);
//
//    void deleteUser(Long id);
//
//    void updateUser(User user);
//
//    User queryUserById(@Param("id") Long id);

    List<User> queryUserByIds(@Param("ids") List<Long> ids);


    void updateBalanceByIds(@Param(Constants.WRAPPER) UpdateWrapper<User> wrapper,@Param("amount") int amount);
}
