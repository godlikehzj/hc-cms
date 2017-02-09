package com.hongchao.cms.service;

import com.hongchao.cms.bean.AchieveHistory;
import com.hongchao.cms.bean.AdminUser;
import com.hongchao.cms.bean.Order;
import com.hongchao.cms.bean.User;
import com.hongchao.cms.service.mapper.UserMapper;
import com.hongchao.cms.util.ResponseEntity;
import com.hongchao.cms.util.SysApiStatus;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by godlikehzj on 2017/1/21.
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public AdminUser login(AdminUser adminUser){
        AdminUser temp = userMapper.getAdminUser(adminUser.getPassport());
        if (temp == null){
            return null;
        }
        String password = DigestUtils.md5Hex(adminUser.getPassword());
        if (password.equals(temp.getPassword())){
            return temp;
        }
        return null;
    }

    public List<User> getUsers(int role, int statu, int province, int city, int district){
        return userMapper.getUsers(role, statu, province, city, district);
    }

    public ResponseEntity addUser(int role, String mobile, String name, String houseIds, int province, int city, int district){
        if (userMapper.getUserByMobile(mobile) != null){
            return new ResponseEntity(1, "手机用户已存在","");
        }
        userMapper.addUser(role, mobile, name, houseIds, province, city, district);
        return new ResponseEntity(SysApiStatus.OK, SysApiStatus.getMessage(SysApiStatus.OK), "");
    }

    public ResponseEntity changeStatus(Long userId, int statu){
        if (statu == 1){
            statu = 0;
        }else{
            statu =1;
        }
        userMapper.changeStatu(userId, statu);
        return new ResponseEntity(SysApiStatus.OK, SysApiStatus.getMessage(SysApiStatus.OK), "");
    }

    public User getUserById(long id){
        return userMapper.getUserByid(id);
    }

    public ResponseEntity editUser(long id, String mobile, String name, String houseIds){
        userMapper.editUser(houseIds, mobile, name, id);
        return new ResponseEntity(SysApiStatus.OK, SysApiStatus.getMessage(SysApiStatus.OK), "");
    }
    public List<AchieveHistory> getAchieveHistoryByUser(long userId){
        return userMapper.getAchieveHisByUser(userId);
    }

    public List<Order> getOrderHistoryByUser(long userId){
        return userMapper.getOrderHistory(userId);
    }
}
