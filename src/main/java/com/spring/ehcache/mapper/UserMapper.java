package com.spring.ehcache.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spring.ehcache.dao.UserDO;
import org.apache.catalina.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

/**
 * @author wangjie
 * @create 2020-09-02-19:59
 * @package IntelliJ IDEA
 */
@Repository
@CacheConfig(cacheNames = "users")
public interface UserMapper extends BaseMapper<UserDO> {
    @Cacheable(key = "#id")
    UserDO selectById(Integer id);

    @CachePut(key = "#user.id")
    default UserDO insert0(UserDO user) {
        this.insert(user);
        return user;
    }
    @CacheEvict(key = "#id")
    int deleteById(Integer id);
}
