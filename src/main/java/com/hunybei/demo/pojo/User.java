package com.hunybei.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@ToString
@Entity
@NoArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;
    private String userName;
    private Integer money;

    public User(Integer id, Integer money) {
        this.id = id;
        this.money = money;
    }

    public User(Integer id, String userName, Integer money) {
        this.id = id;
        this.userName = userName;
        this.money = money;
    }
}
