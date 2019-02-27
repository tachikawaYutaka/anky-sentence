package com.wakabatimes.ankysentence.app.infrastructure.user.mapper;

import com.wakabatimes.ankysentence.app.infrastructure.user.dto.UserDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT * from anky_user u\n" +
            "WHERE u.mail_address = #{mailAddress};")
    UserDto getUserByMail(UserDto input);

    @Select("SELECT * from anky_user u;")
    List<UserDto> list();

    @Insert("INSERT \n" +
            "INTO anky_user( \n" +
            "  id\n" +
            "  , mail_address\n" +
            "  , password\n" +
            "  , status\n" +
            "  , role\n" +
            "  , created\n" +
            "  , updated\n" +
            ") \n" +
            "VALUES ( \n" +
            "  #{id}\n" +
            "  , #{mailAddress}\n" +
            "  , #{password}\n" +
            "  , #{status}\n" +
            "  , #{role}\n" +
            "  , now()\n" +
            "  , now()\n" +
            ");")
    void save(UserDto input);

    @Update("UPDATE anky_user SET status=#{status} where id = #{id}")
    void activate(UserDto userDto);

    @Select("SELECT * from anky_user where mail_address = #{mailAddress}")
    UserDto getUserByMailAddress(UserDto userDto);

    @Update("UPDATE anky_user \n" +
            "SET\n" +
            "   mail_address = #{mailAddress}\n" +
            "  , password = #{password}\n" +
            "  , status = #{status}\n" +
            "  , role = #{role}\n" +
            "  , updated = now()\n" +
            "WHERE\n" +
            "  id = #{id}")
    void update(UserDto userDto);

    @Delete("DELETE from anky_user where id = #{id}")
    void delete(UserDto userDto);

    @Select("SELECT COUNT(*) from anky_user where id = #{id} and mail_address = #{mailAddress}")
    Long countUserByIdAndMail(UserDto userDto);

    @Update("UPDATE anky_user SET password=#{password} where id = #{id}")
    void updateUserPassword(UserDto userDto);

    @Select("SELECT * from anky_user u\n" +
            "WHERE u.id = #{id};")
    UserDto getById(UserDto input);
}
