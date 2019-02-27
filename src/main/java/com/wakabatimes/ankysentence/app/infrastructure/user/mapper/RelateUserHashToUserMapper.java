package com.wakabatimes.ankysentence.app.infrastructure.user.mapper;

import com.wakabatimes.ankysentence.app.domain.model.user.UserHash;
import com.wakabatimes.ankysentence.app.infrastructure.user.dto.RelateUserHashToUserDto;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RelateUserHashToUserMapper {

    @Insert("INSERT INTO relate_user_hash_to_user (id,hash,created,updated) VALUES (#{id},#{hash},now(),now())")
    void save(RelateUserHashToUserDto relateUserHashToUserDto);

    @Select("SELECT COUNT(*) from relate_user_hash_to_user where id = #{id} and hash = #{hash}")
    Long countUserHashAndUserId(RelateUserHashToUserDto relateUserHashToUserDto);

    @Delete("DELETE FROM relate_user_hash_to_user WHERE id = #{id}")
    void delete(String id);

    @Select("SELECT * from relate_user_hash_to_user where hash = #{hash}")
    RelateUserHashToUserDto getUserIdByHash(RelateUserHashToUserDto relateUserHashToUserDto);

    @Select("SELECT COUNT(*) from relate_user_hash_to_user where hash = #{hash}")
    Long countUserHash(RelateUserHashToUserDto relateUserHashToUserDto);

    @Select("SELECT * from relate_user_hash_to_user where id = #{id}")
    RelateUserHashToUserDto getHashByUserId(RelateUserHashToUserDto relateUserHashToUserDto);
}
