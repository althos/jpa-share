package com.athos.jpa.jpashare.repository;

import com.athos.jpa.jpashare.bean.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    List<Account> findAllByUsernameLike(String arg);

    List<Account> findAllByIdAndUsername(Integer id,String name);

    List<Account> findAllByIdAndUsernameAndPassword(Integer id,String name,String pwd);
    

    boolean existsAccountById(Integer id);

    //自定义SQL语句必须在事务环境下运行 必须有DML支持(Modifying)  ?2表示下面的形参的第二个位置 这里不对表进行操作 直接对实体类进行操作 然后实体类映射到表中
    @Transactional//这个注解也可以加到测试类上面 但需要跟进一个@commit提交事务的注解 因为测试类会自动回滚事务
    @Modifying
    @Query("update Account set password=?2 where id=?1")
    int updatePasswordById(int id,String newPassword);


    @Modifying
    @Query(value = "update account set password=?2 where name=?1",nativeQuery = true)//开启原生SQL
    @Transactional
    int updatePasswordByUsername(String username,String password);
}