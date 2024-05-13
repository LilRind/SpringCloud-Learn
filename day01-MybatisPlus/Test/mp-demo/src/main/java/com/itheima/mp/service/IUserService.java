package com.itheima.mp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.mp.domain.po.User;

// Ctrl + Shift + T 创建单元测试
public interface IUserService extends IService<User> {
    public void deductBalance(Long id, Integer money);

}
