package com.hongchao.cms.service;

import com.hongchao.cms.bean.MallCommodity;
import com.hongchao.cms.service.mapper.MallMapper;
import com.hongchao.cms.util.ResponseEntity;
import com.hongchao.cms.util.SysApiStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by godlikehzj on 2017/5/31.
 */
@Service
public class MallService {

    @Autowired
    private MallMapper mallMapper;

    public List<MallCommodity> getCommodityList(int status){
        return mallMapper.getCommodityList(status);
    }

    public MallCommodity getCommodityById(long id){
        return mallMapper.getCommodityById(id);
    }

    public void addCommodity(MallCommodity commodity){
        mallMapper.addCommodity(commodity);
    }
    public ResponseEntity changeStatus(long id, int statu){
        if (statu == 1){
            statu = 0;
        }else{
            statu =1;
        }
        mallMapper.changeStatu(id, statu);
        return new ResponseEntity(SysApiStatus.OK, SysApiStatus.getMessage(SysApiStatus.OK), "");
    }
}
