package com.athos.jpa.jpashare.bean;

import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.List;

@Data
@Entity   //表示这个类是一个实体类
@Table(name = "users")    //对应的数据库中表名称
public class Account {
    @GeneratedValue(strategy = GenerationType.IDENTITY)   //生成策略，这里配置为自增
    @Column(name = "id")    //对应表中id这一列
    @Id                     //此属性为主键
    int id;
    @Column(name = "username")   //对应表中username这一列
    String username;
    @Column(name = "password")   //对应表中password这一列
    String password;

    //一对一
    @JoinColumn(name = "detail_id")
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL) //设置关联操作为ALL
            AccountDetail detail;//对象类型,也可以理解这里写哪个实体类,外键就指向哪个实体类的主键

    //一对多
    @JoinColumn(name = "account_id")
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    //设置关联操作为ALL
            List<AccountGirlFriend> girlFriendList;
    // detail;//对象类型,也可以理解这里写哪个实体类,外键就指向哪个实体类的主键
}