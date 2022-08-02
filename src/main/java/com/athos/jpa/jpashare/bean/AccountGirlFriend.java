package com.athos.jpa.jpashare.bean;

import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;

/**
 * @author cyber.pan
 * @Classname AccountGirlFriend
 * @Description
 * @Date 2022/7/31 21:43
 */

@Data
@Entity
@Table(name = "account_girl_friend")
public class AccountGirlFriend {
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)//还是设置一个自增主键
    @Id
    int id;

    @Column(name = "account_id")
    int accountId;

    @Column(name = "name")
    String name;
}
