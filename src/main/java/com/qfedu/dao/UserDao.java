package com.qfedu.dao;

import com.qfedu.pojo.User;

import java.util.List;

/**
 * Created by Administrator on 2019/6/13.
 */
public interface UserDao {

    List<String> findRolesByName(String name);

    List<String> findPermsByName(String name);

    User findByName(String name);
}
