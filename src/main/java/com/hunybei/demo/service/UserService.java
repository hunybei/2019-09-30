package com.hunybei.demo.service;

import com.hunybei.demo.dao.UserRepository;
import com.hunybei.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(rollbackFor = RuntimeException.class)
    public void updateByUserId(User user) {

        // 模拟耗时 1S
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
        userRepository.save(user);
    }

    public User findById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> findByList(List<Integer> list) {
        Specification spec = (root, query, criteriaBuilder) -> {
            //in
            Predicate predicate = criteriaBuilder.conjunction();
            if (list.size() > 0) {
                CriteriaBuilder.In<Object> in = criteriaBuilder.in(root.get("id"));
                for (Integer id : list) {
                    in.value(id);
                }
                predicate = criteriaBuilder.and(in);
            }
            return query.where(predicate).getRestriction();
        };
        return userRepository.findAll(spec);
    }
}
