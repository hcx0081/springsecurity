package com.springsecurity.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.springsecurity.entity.User;
import com.springsecurity.mapper.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * {@code @Description:}
 */
@Service
public class MyUserDetailsService implements UserDetailsService, UserDetailsPasswordService {
    @Resource
    private UserMapper userMapper;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        return user;
    }
    
    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        User userData = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, user.getUsername()));
        userData.setPassword(newPassword);
        int i = userMapper.updateById(userData);
        System.out.println(user);
        if (i == 1) {
            ((User) user).setPassword(newPassword);
        }
        return user;
    }
}