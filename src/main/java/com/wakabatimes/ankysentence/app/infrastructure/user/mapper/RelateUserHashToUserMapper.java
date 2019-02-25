package com.wakabatimes.ankysentence.app.infrastructure.user.mapper;

import com.wakabatimes.ankysentence.app.infrastructure.user.dto.RelateUserHashToUserDto;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RelateUserHashToUserMapper {

    @Insert("")
    void save(RelateUserHashToUserDto relateUserHashToUserDto);

    @Delete("")
    void delete(String value);

    @Select("")
    Long countUserHashAndUserId(RelateUserHashToUserDto relateUserHashToUserDto);

    @Select("")
    RelateUserHashToUserDto getUserIdByHash(RelateUserHashToUserDto relateUserHashToUserDto);

    @Select("")
    Long countUserHash(RelateUserHashToUserDto relateUserHashToUserDto);
}
