package com.hongchao.cms.service.mapper;

import com.hongchao.cms.bean.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by godlikehzj on 2017/1/12.
 */
public interface HouseMapper {
    public List<HouseInfo> getHouseInfo(@Param("status") int status,
                                        @Param("province") int province,
                                        @Param("city") int city,
                                        @Param("district") int district);
    public List<HouseInfo> getHouseListByUid(@Param("status") int status,
                                             @Param("province") int province,
                                             @Param("city") int city,
                                             @Param("district") int district,
                                             @Param("uid") long uid);
    public void createHouse(@Param("hid") Long hid,
                            @Param("hname") String hname,
                            @Param("addr") String addr,
                            @Param("lng") Double lng,
                            @Param("lat") Double lat,
                            @Param("province") int province,
                            @Param("city") int city,
                            @Param("district") int district);

    public void editHouse(@Param("hname") String hname,
                            @Param("addr") String addr,
                            @Param("lng") Double lng,
                            @Param("lat") Double lat,
                          @Param("houseId") long houseId);

    public void changeStatu(@Param("hid") long hid, @Param("statu") int statu);
    public HouseInfo getHouseById(@Param("houseId") long houseId);
    public List<FixHistory> getFixHistory(@Param("houseId") long houseId, @Param("from") String from, @Param("to") String to);
    public List<FixContent> getFixContents();
    public List<User> getUsersByhouseId(@Param("houseId") long houseId, @Param("role") int role);
    public List<AchieveHistory> getAchieveHis(@Param("houseId") long houseId, @Param("type") int type, @Param("from") String from, @Param("to") String to);
    public List<Order> getOrderHistory(@Param("houseId") long houseId, @Param("from") String from, @Param("to") String to);
    public List<HandleHistory> getHandleStatus(@Param("houseId") long houseId);
    public List<HandleHistory> getHandleStatusByUser(@Param("uid") long uid);

    public List<HandleHistory> getAllHandleHistory(@Param("type") int type);

    public List<WeightHistory> getWeightHistory(@Param("houseId") long houseId);
    public List<WeightHistory> getSortHistory(@Param("houseId") long houseId, @Param("from") String from, @Param("to") String to);
    public List<WeightHistory> getSortHistoryByUser(@Param("userId") long userId);
    public List<WeightHistory> getAllSortHistory();
    public void updateHandleStatu(@Param("id") long id, @Param("statu") int statu);

    public List<CustomerAction> getCustormerAction(@Param("houseId") long houseId, @Param("from") String from, @Param("to") String to);
    public Integer getPutTimes(@Param("hid") long hid, @Param("from") String from, @Param("to") String to);
    public List<CustomerWeight> getCustomerWeightByHid(@Param("hid") long hid, @Param("from") String from, @Param("to") String to);
    public List<CabinetInfo> getCabinetInfo(@Param("houseId") long houseId);

}
