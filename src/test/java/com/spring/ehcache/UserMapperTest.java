package com.spring.ehcache;

import com.spring.ehcache.dao.UserDO;
import com.spring.ehcache.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.UUID;

/**
 * @author wangjie
 * @create 2020-09-02-20:05
 * @package IntelliJ IDEA
 */
// UserMapperTest.java

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EhcacheApplication.class)
public class UserMapperTest {
    public static final String CACHE_NAME_USER = "users";
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CacheManager cacheManager;

    @Test
    public void testCacheManage() {
        System.out.println(cacheManager);
    }
    @Test
    public void testSelectById() {
        Integer id = 1;
        UserDO user = userMapper.selectById(id);
        System.out.println("user:" + user);
        Assert.assertNotNull("缓存为空", cacheManager.getCache(CACHE_NAME_USER).get(user.getId(), UserDO.class));
        user = userMapper.selectById(id);
        System.out.println("user：" + user);
    }
    @Test
    public void testInsert() {
        UserDO user = new UserDO.UserDOBuilder()
                .withUsername(UUID.randomUUID().toString())
                .withPassword("nicai")
                .withCreateTime(new Date())
                .withDeleted(0)
                .build();
        userMapper.insert0(user);
        Assert.assertNotNull("缓存为空", cacheManager.getCache(CACHE_NAME_USER).get(user.getId(), UserDO.class));
    }
    @Test
    public void testDelectById() {
        UserDO user = new UserDO.UserDOBuilder()
                .withUsername(UUID.randomUUID().toString())
                .withPassword("nicai")
                .withCreateTime(new Date())
                .withDeleted(0)
                .build();
        userMapper.insert0(user);
        Assert.assertNotNull("缓存为空",cacheManager.getCache(CACHE_NAME_USER).get(user.getId(),UserDO.class));
        userMapper.deleteById(user.getId());
        Assert.assertNull("缓存不为空", cacheManager.getCache(CACHE_NAME_USER).get(user.getId(), UserDO.class));
    }


}
