package com.wakabatimes.ankysentence.app.infrastructure.user;

import com.wakabatimes.ankysentence.app.domain.model.user.*;
import com.wakabatimes.ankysentence.app.infrastructure.user.dto.RelateUserHashToUserDto;
import com.wakabatimes.ankysentence.app.infrastructure.user.dto.UserDto;
import com.wakabatimes.ankysentence.app.infrastructure.user.mapper.RelateUserHashToUserMapper;
import com.wakabatimes.ankysentence.app.infrastructure.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RelateUserHashToUserMapper relateUserHashToUserMapper;

    @Override
    public User getUserByMail(String userMailAddress) {
        UserDto input = new UserDto();
        input.setMailAddress(userMailAddress);
        UserDto userDto = userMapper.getUserByMail(input);

        UserId userId = new UserId(userDto.getId());
        UserMailAddress userMailAddress2 = new UserMailAddress(userDto.getMailAddress());
        UserPassword userPassword = new UserPassword(userDto.getPassword());
        UserStatus userStatus = UserStatus.getById(userDto.getStatus());
        UserRole userRole = UserRole.getById(userDto.getRole());

        User user = new User(userId, userMailAddress2, userPassword, userStatus,userRole);
        return user;
    }

    @Override
    public void save(User user) {
        UserDto input = new UserDto();
        input.setId(user.getUserId().getValue());
        input.setMailAddress(user.getUserMailAddress().getValue());
        input.setPassword(user.getUserPassword().getValue());
        input.setStatus(user.getUserStatus().getId());
        input.setRole(user.getUserRole().getId());

        List<UserDto> userList = userMapper.list();
        Users users = new Users();
        for(UserDto userDto : userList) {
            UserId userId = new UserId(userDto.getId());
            UserMailAddress userMailAddress = new UserMailAddress(userDto.getMailAddress());
            UserPassword userPassword = new UserPassword(userDto.getPassword());
            UserStatus userStatus = UserStatus.getById(userDto.getStatus());
            UserRole userRole = UserRole.getById(userDto.getRole());
            User thisUser = new User(userId,userMailAddress,userPassword,userStatus,userRole);
            users.add(thisUser);
        }

        if(!users.contains(user)) {
            userMapper.save(input);
        }else {
            throw new RuntimeException("ユーザー名またはメールアドレスが既に登録されています。");
        }
    }

    @Override
    public void saveHash(User user, String result) {
        RelateUserHashToUserDto relateUserHashToUserDto = new RelateUserHashToUserDto(user.getUserId(), result);
        relateUserHashToUserMapper.save(relateUserHashToUserDto);
    }

    @Override
    public void deleteHash(User user) {
        relateUserHashToUserMapper.delete(user.getUserId().getValue());
    }

    @Override
    public Long countUserHashAndUserId(UserId userId, String hash) {
        RelateUserHashToUserDto relateUserHashToUserDto = new RelateUserHashToUserDto();
        relateUserHashToUserDto.setId(userId.getValue());
        relateUserHashToUserDto.setHash(hash);
        return relateUserHashToUserMapper.countUserHashAndUserId(relateUserHashToUserDto);
    }

    @Override
    public void activate(UserId userId) {
        UserDto userDto = new UserDto();
        userDto.setId(userId.getValue());
        userDto.setStatus(UserStatus.ACTIVATE.getId());
        userMapper.activate(userDto);
    }

    @Override
    public UserId getUserIdByHash(String hash) {
        RelateUserHashToUserDto relateUserHashToUserDto = new RelateUserHashToUserDto();
        relateUserHashToUserDto.setHash(hash);
        RelateUserHashToUserDto result = relateUserHashToUserMapper.getUserIdByHash(relateUserHashToUserDto);
        UserId userId = new UserId(result.getId());
        return userId;
    }

    @Override
    public User getUserByMailAddress(UserMailAddress userMailAddress) {
        UserDto userDto = new UserDto();
        userDto.setMailAddress(userMailAddress.getValue());
        UserDto result =  userMapper.getUserByMailAddress(userDto);

        UserId userId = new UserId(result.getId());
        UserPassword userPassword = new UserPassword(result.getPassword());
        UserStatus userStatus = UserStatus.getById(result.getStatus());
        UserRole userRole = UserRole.getById(result.getRole());
        return new User(userId,userMailAddress,userPassword, userStatus,userRole);
    }

    @Override
    public void update(User user) {
        UserDto userDto = new UserDto(user);
        userMapper.update(userDto);
    }

    @Override
    public void delete(UserId userId) {
        UserDto userDto = new UserDto();
        userDto.setId(userId.getValue());
        userMapper.delete(userDto);
    }

    @Override
    public Long countUserByIdAndMail(UserId userId, UserMailAddress userMailAddress) {
        UserDto userDto = new UserDto();
        userDto.setId(userId.getValue());
        userDto.setMailAddress(userMailAddress.getValue());
        return userMapper.countUserByIdAndMail(userDto);
    }

    @Override
    public Long countUserHash(String hash) {
        RelateUserHashToUserDto relateUserHashToUserDto = new RelateUserHashToUserDto();
        relateUserHashToUserDto.setHash(hash);
        return relateUserHashToUserMapper.countUserHash(relateUserHashToUserDto);
    }

    @Override
    public void updatePassword(UserId userId, UserPassword userPassword) {
        UserDto userDto = new UserDto();
        userDto.setId(userId.getValue());
        userDto.setPassword(userPassword.getValue());
        userMapper.updateUserPassword(userDto);
    }
}
