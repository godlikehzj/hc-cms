package com.hongchao.cms.service;

import com.hongchao.cms.bean.User;
import com.hongchao.cms.service.mapper.UserMapper;
import com.hongchao.cms.util.ResponseEntity;
import com.hongchao.cms.util.SysApiStatus;
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

    public List<User> getUsers(int role, int statu){
        return userMapper.getUsers(role, statu);
    }

    public ResponseEntity addUser(int role, String mobile, String name, String houseIds){
        userMapper.addUser(role, mobile, name, houseIds);
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
}
