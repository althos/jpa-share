package com.athos.jpa.jpashare;

import com.alibaba.fastjson.JSON;
import com.athos.jpa.jpashare.bean.Account;
import com.athos.jpa.jpashare.bean.AccountDetail;
import com.athos.jpa.jpashare.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

@SpringBootTest
class JpaTestApplicationTests {

    @Resource
    AccountRepository repository;

    @Test
    void contextLoads() {
        //直接根据ID查找
        System.out.println(repository.findById(2).map(v-> JSON.toJSONString(v)));
    }

    @Test
    void addAccount(){
        Account account = new Account();
        account.setUsername("cyber");
        account.setPassword("123456");
        account = repository.save(account);  //返回的结果会包含自动生成的主键值
        System.out.println("插入时，自动生成的主键ID为："+account.getId());
    }

    @Test
    void deleteAccount(){
        repository.deleteById(3);   //根据ID删除对应记录
    }

    @Test
    void pageAccount() {
        repository.findAll(PageRequest.of(0, 1)).forEach(System.out::println);  //直接分页
    }


    @Test
    void testLike() {
        repository.findAllByUsernameLike("%min%").forEach(System.out::println);
    }

    @Test
    void testExist() {
        System.out.println(repository.existsById(1));
    }

    @Test
    void testUpdate() {
        System.out.println(repository.updatePasswordById(1,"9090909"));
    }

    @Test
    void pageAccount3() {
        repository.findById(2).ifPresent(account -> {
            System.out.println(account.getUsername());   //获取用户名
            System.out.println(account.getDetail());  //获取详细信息（懒加载）
            //System.out.println(account.getGirlFriendList());
        });
    }


    @Test//一次性添加数据到两张表中
    void add(){
        Account account = new Account();
        account.setUsername("Nike");
        account.setPassword("123456");
        AccountDetail detail = new AccountDetail();
        detail.setAddress("jdiejijijiiiiiiiii");
        detail.setPhone("1234567890");
        detail.setEmail("73281937@qq.com");
        detail.setRealName("cyber");
        account.setDetail(detail);//这里就是传入一个对象
        account = repository.save(account);
        System.out.println("插入时，自动生成的主键ID为："+account.getId()+"，外键ID为："+account.getDetail().getId());
    }

}