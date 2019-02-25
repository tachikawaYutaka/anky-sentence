package com.wakabatimes.ankysentence.app.infrastructure.user.mapper;

import com.wakabatimes.ankysentence.app.infrastructure.user.dto.UserDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("")
    UserDto getUserByMail(UserDto input);

    @Select("")
    List<UserDto> list();

    @Insert("")
    void save(UserDto input);

    @Update("")
    void activate(UserDto userDto);

    @Select("")
    UserDto getUserByMailAddress(UserDto userDto);

    @Update("")
    void update(UserDto userDto);

    @Delete("")
    void delete(UserDto userDto);

    @Select("")
    Long countUserByIdAndMail(UserDto userDto);

    @Update("")
    void updateUserPassword(UserDto userDto);
}
