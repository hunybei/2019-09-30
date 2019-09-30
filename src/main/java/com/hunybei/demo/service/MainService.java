package com.hunybei.demo.service;

import com.hunybei.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
        for (User user : list) {
            User u = userService.findById(user.getId());
            totalMoney += u.getMoney();
        }
        return totalMoney;
    }

    @Transactional
    public Integer main2(List<User> list) {

        // 进行业务处理
        list.forEach(e -> userService.updateByUserId(e));

        // 业务查询 返回结果
        int totalMoney = 0;
        for (User user : list) {
            User u = userService.findById(user.getId());
            totalMoney += u.getMoney();
        }
        return totalMoney;
    }
}
