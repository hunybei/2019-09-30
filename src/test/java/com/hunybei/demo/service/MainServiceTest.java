package com.hunybei.demo.service;

import com.hunybei.demo.DemoApplicationTests;
import com.hunybei.demo.pojo.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MainServiceTest extends DemoApplicationTests {

    @Autowired
    private MainService mainService;
    private static final List<User> list = new ArrayList<>();

    static {
        list.add(new User(1, "张三", 100));
        list.add(new User(2, "李四", 200));
        list.add(new User(3, "王五", 300));
    }

    public final static long value = list.parallelStream().mapToLong(User::getMoney).sum();


    /**
     * 每次执行记得清空一下，数据库里的money值，变成0
     */
    @Test
    public void main1() {
        Integer money = mainService.main1(list);
        assertEquals(value, money.longValue());
    }


    @Test
    @Transactional
    public void main2() {
        Integer money = mainService.main2(list);
        assertEquals(value, money.longValue());
    }

}