package com.nowcoder.community.util;

import com.nowcoder.community.entity.User;
import org.springframework.stereotype.Component;

/**
 * @author pumpkin
 * @date 2022/3/12
 */

/**
 * 持有用户信息，用于替代session对象
 */
@Component
public class HostHolder {
    //ThreadLocal是线程隔离的，存键值对，键是当前线程名
    private ThreadLocal<User> users = new ThreadLocal<>() ;

    public void setUser(User user){
        //set()  user是值，键是当前线程名
        users.set(user);
    }

    public User getUser(){
        return users.get() ;
    }

    public void clear(){
        users.remove(); ;
    }
}
