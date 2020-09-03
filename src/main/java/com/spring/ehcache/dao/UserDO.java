package com.spring.ehcache.dao;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author wangjie
 * @create 2020-09-02-17:42
 * @package IntelliJ IDEA
 */
@Data
@TableName(value = "users")
public class UserDO {

     private Integer id;
    /**
     * 账号
     */
    private String username;
    /**
     * 密码（明文）
     *
     * ps：生产环境下，千万不要明文噢
     */
    private String password;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 是否删除
     */
    @TableLogic
    private Integer deleted;

    public static final class UserDOBuilder {
        private Integer id;
        private String username;
        private String password;
        private Date createTime;
        private Integer deleted;

        public UserDOBuilder() {
        }

        public static UserDOBuilder anUserDO() {
            return new UserDOBuilder();
        }

        public UserDOBuilder withId(Integer id) {
            this.id = id;
            return this;
        }

        public UserDOBuilder withUsername(String username) {
            this.username = username;
            return this;
        }

        public UserDOBuilder withPassword(String password) {
            this.password = password;
            return this;
        }

        public UserDOBuilder withCreateTime(Date createTime) {
            this.createTime = createTime;
            return this;
        }

        public UserDOBuilder withDeleted(Integer deleted) {
            this.deleted = deleted;
            return this;
        }

        public UserDO build() {
            UserDO userDO = new UserDO();
            userDO.setId(id);
            userDO.setUsername(username);
            userDO.setPassword(password);
            userDO.setCreateTime(createTime);
            userDO.setDeleted(deleted);
            return userDO;
        }
    }
}
