package com.qfedu.controller;

import com.qfedu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2019/6/13.
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;
}
