package com.hongchao.cms.service;

import com.hongchao.cms.bean.HouseInfo;
import com.hongchao.cms.service.mapper.HouseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by godlikehzj on 2017/1/12.
 */
@Service
public class HouseService {
    @Autowired
    private HouseMapper houseMapper;

    public List<HouseInfo> getHouseList(int status){
        return houseMapper.getHouseInfo(status);
    }
}
