package com.qfedu.service.impl;

import com.qfedu.dao.UserDao;
import com.qfedu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2019/6/13.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
}
