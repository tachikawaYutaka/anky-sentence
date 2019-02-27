package com.wakabatimes.ankysentence.app.domain.service.user;

import com.wakabatimes.ankysentence.AnkySentenceApplication;
import com.wakabatimes.ankysentence.app.domain.model.user.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.security.NoSuchAlgorithmException;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AnkySentenceApplication.class)
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JdbcOperations jdbcOperations; // 各テスト前処理用
    @Before
    public void eachBefore() {
        jdbcOperations.execute("SET FOREIGN_KEY_CHECKS=0");
        jdbcOperations.execute("DELETE FROM anky_user");
        jdbcOperations.execute("DELETE FROM relate_user_hash_to_user");
        jdbcOperations.execute("SET FOREIGN_KEY_CHECKS=1");
    }

    @After
    public void eachAfter() {
        jdbcOperations.execute("SET FOREIGN_KEY_CHECKS=0");
        jdbcOperations.execute("DELETE FROM anky_user");
        jdbcOperations.execute("DELETE FROM relate_user_hash_to_user");
        jdbcOperations.execute("SET FOREIGN_KEY_CHECKS=1");
    }

    @Test
    public void save(){
        UserMailAddress userMailAddress = new UserMailAddress("hogehoge@hogehoge.com");
        UserPassword userPassword = new UserPassword("hogehoge",bCryptPasswordEncoder);
        User user = UserFactory.create(userMailAddress, userPassword);
        userService.save(user);
    }

    @Test
    public void activateMail(){
        UserMailAddress userMailAddress = new UserMailAddress("hogehoge@hogehoge.com");
        UserPassword userPassword = new UserPassword("hogehoge",bCryptPasswordEncoder);
        User user = UserFactory.create(userMailAddress, userPassword);
        try {
            userService.activateMail(user);
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void activate(){
        UserMailAddress userMailAddress = new UserMailAddress("hogehoge@hogehoge.com");
        UserPassword userPassword = new UserPassword("hogehoge",bCryptPasswordEncoder);
        User user = UserFactory.create(userMailAddress, userPassword);
        userService.save(user);
        try {
            userService.activateMail(user);
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage());
        }
        UserHash userHash = userService.getHashByUserId(user.getUserId());
        userService.activate(user.getUserMailAddress(),userHash);
    }

    @Test
    public void getUserByMailAddress(){
        UserMailAddress userMailAddress = new UserMailAddress("hogehoge@hogehoge.com");
        UserPassword userPassword = new UserPassword("hogehoge",bCryptPasswordEncoder);
        User user = UserFactory.create(userMailAddress, userPassword);
        userService.save(user);
        try {
            userService.passwordRemindMail(user.getUserMailAddress());
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void passwordReset(){
        UserMailAddress userMailAddress = new UserMailAddress("hogehoge@hogehoge.com");
        UserPassword userPassword = new UserPassword("hogehoge",bCryptPasswordEncoder);
        User user = UserFactory.create(userMailAddress, userPassword);
        userService.save(user);
        try {
            userService.passwordRemindMail(user.getUserMailAddress());
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage());
        }
        UserHash userHash = userService.getHashByUserId(user.getUserId());
        UserPassword userPassword1 = new UserPassword("newPassword");
        userService.passwordReset(userHash,userPassword1);
    }

    @Test
    public void delete(){
        UserMailAddress userMailAddress = new UserMailAddress("hogehoge@hogehoge.com");
        UserPassword userPassword = new UserPassword("hogehoge",bCryptPasswordEncoder);
        User user = UserFactory.create(userMailAddress, userPassword);
        userService.save(user);
        User user1 = userService.getById(user.getUserId());
        userService.delete(user1.getUserId(),user.getUserMailAddress());
    }

    @Test
    public void update(){
        UserMailAddress userMailAddress = new UserMailAddress("hogehoge@hogehoge.com");
        UserPassword userPassword = new UserPassword("hogehoge",bCryptPasswordEncoder);
        User user = UserFactory.create(userMailAddress, userPassword);
        userService.save(user);

        UserMailAddress userMailAddress1 = new UserMailAddress("aaa@aaa.aaa");
        UserPassword userPassword1 = new UserPassword("aaaaa",bCryptPasswordEncoder);
        User user1 = new User(user.getUserId(),userMailAddress1,userPassword1,user.getUserStatus(),user.getUserRole());
        userService.update(user1);
    }
}
