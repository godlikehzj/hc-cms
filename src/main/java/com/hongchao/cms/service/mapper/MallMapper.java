package com.hongchao.cms.service.mapper;

import com.hongchao.cms.bean.MallCommodity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by godlikehzj on 2017/5/31.
 */
public interface MallMapper {
    public List<MallCommodity> getCommodityList(@Param("statu") int statu);
    public void changeStatu(@Param("id") long id, @Param("statu") int statu);
    public MallCommodity getCommodityById(@Param("id") long id);
    public void addCommodity(MallCommodity commodity);
}
