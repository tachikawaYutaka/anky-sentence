package com.wakabatimes.ankysentence.app.application.user;

import com.wakabatimes.ankysentence.app.domain.model.user.User;
import com.wakabatimes.ankysentence.app.domain.service.user.UserService;
import com.wakabatimes.ankysentence.app.domain.service.user.UserSubscribeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;

@Service
@Slf4j
public class UserSubscribeServiceImpl implements UserSubscribeService{
    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public void save(User user) {
        userService.save(user);
        try {
            userService.activateMail(user);
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
}
