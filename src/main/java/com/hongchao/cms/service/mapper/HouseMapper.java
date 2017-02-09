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
    public void createHouse(@Param("hname") String hname,
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
    public List<FixHistory> getFixHistory(@Param("houseId") long houseId);
    public List<FixContent> getFixContents();
    public List<User> getUsersByhouseId(@Param("houseId") long houseId, @Param("role") int role);
    public List<AchieveHistory> getAchieveHis(@Param("houseId") long houseId);
    public List<Order> getOrderHistory(@Param("houseId") long houseId);

}
