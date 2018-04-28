package com.liucz.ehcache.service;

import com.liucz.ehcache.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Resource
    private CacheManager cacheManager;
    @Autowired
    UserService userService;

    @Test
    public void cacheTest() {
        // 显示所有的Cache空间
        System.out.println(cacheManager.getCacheNames());

        Cache cache = cacheManager.getCache("user");
        cache.put("key", "123");
        System.out.println("缓存成功");
        String res = cache.get("key", String.class);
        System.out.println(res);
    }

    @Test
    public void save() {

        User user = new User();
        user.setUsername("username1");
        user.setPassword("password1");
        userService.save(user);

        Cache cache = cacheManager.getCache("user");
        User res = cache.get(user.getUsername(), User.class);
        System.out.println("查询缓存数据："+res.toString());

    }

    @Test
    public void remove() {

        User user = new User();
        user.setUsername("username2");
        user.setPassword("password2");
        userService.save(user);

        Cache cache = cacheManager.getCache("user");
        User res1 = cache.get(user.getUsername(), User.class);
        System.out.println("查询缓存数据1："+res1.toString());

        //执行删除操作
        userService.remove(user.getUsername());

        User res2 = cache.get(user.getUsername(), User.class);
        System.out.println("查询缓存数据2："+res2);

    }

    @Test
    public void findOne() {

        User user = new User();
        user.setUsername("username3");
        user.setPassword("password3");
        userService.findOne(user);

        Cache cache = cacheManager.getCache("user");
        User res = cache.get(user.getUsername(), User.class);
        System.out.println("查询缓存数据："+res.toString());

    }

}
