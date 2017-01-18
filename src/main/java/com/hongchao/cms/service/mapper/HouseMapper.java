package com.hongchao.cms.service.mapper;

import com.hongchao.cms.bean.HouseInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by godlikehzj on 2017/1/12.
 */
public interface HouseMapper {
    public List<HouseInfo> getHouseInfo(@Param("status") int status);
    public void createHouse(@Param("hname") String hname,
                            @Param("addr") String addr,
                            @Param("lng") Double lng,
                            @Param("lat") Double lat);
}
