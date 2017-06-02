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
        List<HouseInfo> houseInfos = houseMapper.getHouseInfo(status, province, city, district);
        for(HouseInfo houseInfo : houseInfos){
            String[] capacitys = houseInfo.getCapacity().split(",");
            houseInfo.setCapacity("未满");
            for(String capacity : capacitys){
                if (Integer.valueOf(capacity) > 85){
                    houseInfo.setCapacity("已满");
                    break;
                }
            }
        }
        return houseInfos;
    }

    public List<HouseInfo> getHouseListByUid(int status, int province, int city, int district, long uid){
        List<HouseInfo> houseInfos = houseMapper.getHouseListByUid(status, province, city, district, uid);
        return houseInfos;
    }

    public HouseInfo getHouseById(long id){
        return houseMapper.getHouseById(id);
    }

    public List<HandleHistory> getHandleStatus(long id){
        return houseMapper.getHandleStatus(id);
    }

    public List<HandleHistory> getHandleStatusByUser(long uid){
        return houseMapper.getHandleStatusByUser(uid);
    }
    public List<FixHistory> getFixHistory(long houseId, String from, String to){
        List<FixHistory> list = houseMapper.getFixHistory(houseId, from, to);
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

    public ResponseEntity addHouse(Long hid, String hname, String addr, String location, int province, int city, int district){
        String[] locations = location.split(",");
        Double lng = Double.valueOf(locations[0]);
        Double lat = Double.valueOf(locations[1]);
        houseMapper.createHouse(hid, hname, addr, lng, lat, province, city, district);
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

    public List<AchieveHistory> getAchieveHistory(long houseId, int type, String from, String to){
        return houseMapper.getAchieveHis(houseId, type, from, to);
    }

    public List<Order> getOrderHistory(long houseId,  String from, String to){
        return houseMapper.getOrderHistory(houseId, from, to);
    }

    public List<WeightHistory> getWeightHistory(long houseId){
        return houseMapper.getWeightHistory(houseId);
    }

    public List<WeightHistory> getSortHistory(long houseId, String from, String to){
        return houseMapper.getSortHistory(houseId, from, to);
    }

    public List<CustomerAction> getCustomerAction(long houseId, String from, String to){
        return houseMapper.getCustormerAction(houseId, from, to);
    }

    public List<WeightHistory> getSortHistoryByUser(long userId,String from, String to){
        return houseMapper.getSortHistory(userId, from, to);
    }

    public List<WeightHistory> getAllSortHistory(){
        return houseMapper.getAllSortHistory();
    }

    public List<HandleHistory> getAllHandleHistory(int type){
        return houseMapper.getAllHandleHistory(type);
    }

    public void updateHandleStatu(long id, int statu){
        houseMapper.updateHandleStatu(id, statu);
    }

    public Integer getPutTimes(long id, String from, String to){
        return houseMapper.getPutTimes(id, from, to);
    }

    public List<CustomerWeight> getCustomerWeightList(long houseId, String from, String to){
        return houseMapper.getCustomerWeightByHid(houseId, from, to);
    }

    public List<CabinetInfo> getCabinetInfo(long houseId){
        return houseMapper.getCabinetInfo(houseId);
    }
}
