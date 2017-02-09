package com.hongchao.cms.service;

import com.hongchao.cms.bean.*;
import com.hongchao.cms.service.mapper.HouseMapper;
import com.hongchao.cms.util.Config;
import com.hongchao.cms.util.ResponseEntity;
import com.hongchao.cms.util.SysApiStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by godlikehzj on 2017/1/12.
 */
@Service
public class HouseService {
    @Autowired
    private HouseMapper houseMapper;

    public List<HouseInfo> getHouseList(int status, int province, int city, int district){
        return houseMapper.getHouseInfo(status, province, city, district);
    }

    public HouseInfo getHouseById(long id){
        return houseMapper.getHouseById(id);
    }

    public List<FixHistory> getFixHistory(long houseId){
        List<FixHistory> list = houseMapper.getFixHistory(houseId);
        Map<Integer, String> fixContents = Config.getFixContents();
        for(FixHistory fixHistory : list){
            String[] fids = fixHistory.getContent().split(",");
            String content = "";
            for(String fid : fids){
                content += fixContents.get(Integer.valueOf(fid)) + " ";
            }
            fixHistory.setContent(content);
        }
        return list;
    }

    public ResponseEntity addHouse(String hname, String addr, String location, int province, int city, int district){
        String[] locations = location.split(",");
        Double lng = Double.valueOf(locations[0]);
        Double lat = Double.valueOf(locations[1]);
        houseMapper.createHouse(hname, addr, lng, lat, province, city, district);
        return new ResponseEntity(SysApiStatus.OK, SysApiStatus.getMessage(SysApiStatus.OK), "");
    }

    public ResponseEntity editHouse(long id, String hname, String addr, String location){
        String[] locations = location.split(",");
        Double lng = Double.valueOf(locations[0]);
        Double lat = Double.valueOf(locations[1]);
        houseMapper.editHouse(hname, addr, lng, lat, id);
        return new ResponseEntity(SysApiStatus.OK, SysApiStatus.getMessage(SysApiStatus.OK), "");
    }

    public ResponseEntity changeStatus(Long houseId, int statu){
        if (statu == 1){
            statu = 0;
        }else{
            statu =1;
        }
        houseMapper.changeStatu(houseId, statu);
        return new ResponseEntity(SysApiStatus.OK, SysApiStatus.getMessage(SysApiStatus.OK), "");
    }

    public List<User> getUsersByhouseId(long houseId, int role){
        return houseMapper.getUsersByhouseId(houseId, role);
    }

    public List<AchieveHistory> getAchieveHistory(long houseId){
        return houseMapper.getAchieveHis(houseId);
    }

    public List<Order> getOrderHistory(long houseId){
        return houseMapper.getOrderHistory(houseId);
    }
}
