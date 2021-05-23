package com.prespringboot.community.forum.service;

import com.prespringboot.community.forum.mapper.UserMapper;
import com.prespringboot.community.forum.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void createOrUpdate(User user) {
        User selectUser = userMapper.selectUser(user.getAccountId());
        if (selectUser == null){
            //插入
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        }else {
            //更新
            selectUser.setGmtModified(System.currentTimeMillis());
            selectUser.setAvatarUrl(user.getAvatarUrl());
            selectUser.setName(user.getName());
            selectUser.setToken(user.getToken());
            userMapper.update(selectUser);
        }
    }
}
