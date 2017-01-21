package com.hongchao.cms.service.mapper;

import com.hongchao.cms.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by godlikehzj on 2017/1/9.
 */
public interface UserMapper {
    public List<User> getUsers(@Param("role") int role, @Param("status") int status);
    public void addUser(@Param("role") int role,
                        @Param("mobile") String mobile,
                        @Param("name") String name,
                        @Param("houseIds") String houseIds);
    public void changeStatu(@Param("userId") long userId, @Param("status") int status);

    public User getUserByid(@Param("userId") long userId);

    public void editUser(@Param("houseIds") String houseIds,
                         @Param("mobile") String mobile,
                         @Param("name") String name,
                         @Param("userId") long userId);
}
