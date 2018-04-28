package com.liucz.ehcache.service;

import com.liucz.ehcache.model.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @CachePut(value = "user", key = "#user.username")
    public User save(User user) {
        System.out.println("@CachePut，为username、key为:" + user.getUsername() + "的数据做了缓存");
        return user;
    }

    @CacheEvict(value = "user")
    public void remove(String username) {
        System.out.println("@CacheEvict，删除username、key为" + username + "的数据缓存");
        //这里不做实际删除操作
    }

    @Cacheable(value = "user", key = "#user.username")//3
    public User findOne(User user) {
        System.out.println("@Cacheable，为username、key为:" + user.getUsername() + "的数据做了缓存");
        return user;
    }

}
