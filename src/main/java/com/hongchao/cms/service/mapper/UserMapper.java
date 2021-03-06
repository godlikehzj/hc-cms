package com.hongchao.cms.service.mapper;

import com.hongchao.cms.bean.AchieveHistory;
import com.hongchao.cms.bean.AdminUser;
import com.hongchao.cms.bean.Order;
import com.hongchao.cms.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by godlikehzj on 2017/1/9.
 */
public interface UserMapper {
    public List<User> getUsers(@Param("role") int role,
                               @Param("status") int status,
                               @Param("province") int province,
                               @Param("city") int city,
                               @Param("district") int district);
    public List<User> getSortUsers(@Param("status") int status,
                                   @Param("province") int province,
                                   @Param("city") int city,
                                   @Param("district") int district);
    public void addUser(@Param("role") int role,
                        @Param("mobile") String mobile,
                        @Param("name") String name,
                        @Param("houseIds") String houseIds,
                        @Param("province") int province,
                        @Param("city") int city,
                        @Param("district") int district);
    public void changeStatu(@Param("userId") long userId, @Param("status") int status);

    public User getUserByid(@Param("userId") long userId);

    public User getUserByMobile(@Param("mobile") String mobile);

    public void editUser(@Param("houseIds") String houseIds,
                         @Param("mobile") String mobile,
                         @Param("name") String name,
                         @Param("userId") long userId);

    public AdminUser getAdminUser(@Param("passport") String passport);
    public List<AchieveHistory> getAchieveHisByUser(@Param("userId") long userId, @Param("from") String from, @Param("to") String to);
    public List<Order> getOrderHistory(@Param("userId") long userId, @Param("from") String from, @Param("to") String to);

    public List<User> getUsersByhouseId(@Param("houseId") long houseId, @Param("role") int role);
//    public long getSorterByHouseId(@Param("houseId") long houseId);
//    public void updateSorterByHouseId(@Param("houseId") long houseId, @Param("uid") long uid);
}
