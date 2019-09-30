package com.hunybei.demo.service;

import com.hunybei.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MainService {


    @Autowired
    private UserService userService;


    @Transactional
    public Integer main1(List<User> list) {

        // 1.进行业务处理
        list.parallelStream().forEach(e -> userService.updateByUserId(e));

        // 2.模拟业务查询 返回结果
        int totalMoney = 0;
        List<User> userList = userService.findByList(list.stream().map(User::getId).collect(Collectors.toList()));
        for (User user : userList) {
            totalMoney += user.getMoney();
        }
        return totalMoney;
    }

    @Transactional
    public Integer main2(List<User> list) {

        // 进行业务处理
        list.forEach(e -> userService.updateByUserId(e));

        // 业务查询 返回结果
        int totalMoney = 0;
        List<User> userList = userService.findByList(list.stream().map(User::getId).collect(Collectors.toList()));
        for (User user : userList) {
            totalMoney += user.getMoney();
        }
        return totalMoney;
    }
}
