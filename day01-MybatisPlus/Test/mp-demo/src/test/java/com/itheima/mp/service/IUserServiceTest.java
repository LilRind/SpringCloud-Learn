package com.itheima.mp.service;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.mp.domain.po.User;
import com.itheima.mp.domain.po.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class IUserServiceTest {

    @Autowired
    private IUserService userService;

    @Test
    void testSaveUser() {
        User user = new User();
        // user.setId(5L);
        user.setUsername("ErGouZi");
        user.setPassword("123");
        user.setPhone("17135154830");
        user.setBalance(200);
//        user.setInfo("{\"age\": 24, \"intro\": \"英文老师\", \"gender\": \"female\"}");
        user.setInfo(UserInfo.of(24, "英文老师", "female"));
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());

        userService.save(user);
    }

    @Test
    void testQuery(){
        List<User> users = userService.listByIds(List.of(1L, 2L, 4L));
        users.forEach(System.out::println);
    }

    // P12
    // 普通for循环逐条插入，速度极差，不推荐

    // MP的批量新增，基于预编译的批处理，性能不错。
    // 但还是执行多次网络请求，执行多次sql语句


    // MP的批量新增，基于预编译的批处理 + 开启rewriteBatchedStatements=true，性能最好
    // 配置jdbc参数，开启rewriteBatchedStatements=true，yaml文件中更改
    @Test
    void testSaveBatch(){
        // 1. 准备容量为1000的集合
        ArrayList<User> list = new ArrayList<>(1000);
        long s = System.currentTimeMillis();
        for (int i = 1; i <= 100000; i++) {
            // 2. 添加一个user
            list.add(buildUser(i));
            // 3. 每1000条批量插入1次
            if(i % 1000 == 0){
                userService.saveBatch(list);
                // 4. 清空集合，准备下一批数据
                list.clear();
            }
        }
        long e = System.currentTimeMillis();
        System.out.println("耗时：" + (e - s)); // 耗时：7684ms
    }

    private User buildUser(int i){
        User user = new User();
        user.setUsername("user_" + i);
        user.setPassword("123");
        user.setPhone("" + (18688190000L + i));
        user.setBalance(2000);
//        user.setInfo("{\"age\": 24, \"intro\": \"英文老师\", \"gender\": \"female\"}");
        user.setInfo(UserInfo.of(24, "英文老师", "female"));
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());

        return user;
    }

    @Test
    void testPageQuery(){
        int pageNo = 1, pageSize = 2;
        // 1.准备分页条件
        // 1.1 分页条件
        Page<User> page = Page.of(pageNo, pageSize);
        // 1.2 排序条件
        page.addOrder(new OrderItem("balance", true));
        page.addOrder(new OrderItem("id", true));

        // 2. 分页查询
        Page<User> p = userService.page(page);

        // 3. 解析
        long total = p.getTotal();
        System.out.println("total = " + total);
        long pages = p.getPages();
        System.out.println("pages = " + pages);
        List<User> users = p.getRecords();
        users.forEach(System.out::println);
    }


}