package com.athos.jpa.jpashare.bean;

import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;

@Data
@Entity
@Table(name = "account_details")
public class AccountDetail {
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)//还是设置一个自增主键
    @Id
    int id;

    @Column(name = "address")
    String address;

    @Column(name = "email")
    String email;

    @Column(name = "phone")
    String phone;

    @Column(name = "real_name")
    String realName;
}
