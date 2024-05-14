package com.itheima.mp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.mp.domain.dto.PageDTO;
import com.itheima.mp.domain.po.User;
import com.itheima.mp.domain.query.UserQuery;
import com.itheima.mp.domain.vo.UserVO;

import java.util.List;

// Ctrl + Shift + T 创建单元测试
public interface IUserService extends IService<User> {
    public void deductBalance(Long id, Integer money);

    List<User> queryUsers(String name, Integer status, Integer minBalance, Integer maxBalance);

    UserVO queryUserAndAddressById(Long id);

    List<UserVO> queryUserAndAddressByIds(List<Long> ids);

    PageDTO<UserVO> queryUsersPage(UserQuery query);
}
