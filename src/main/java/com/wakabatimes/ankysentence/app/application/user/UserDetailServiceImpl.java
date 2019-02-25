package com.wakabatimes.ankysentence.app.application.user;

import com.wakabatimes.ankysentence.app.domain.model.user.User;
import com.wakabatimes.ankysentence.app.domain.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl  implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userMailAddress) throws UsernameNotFoundException {
        User user  = userRepository.getUserByMail(userMailAddress);
        return new User(user.getUserId(),user.getUserMailAddress(),user.getUserPassword(),user.getUserStatus(),user.getUserRole());
    }
}
