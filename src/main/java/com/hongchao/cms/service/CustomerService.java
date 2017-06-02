package com.hongchao.cms.service;

import com.hongchao.cms.bean.CustomerAction;
import com.hongchao.cms.bean.CustomerInfo;
import com.hongchao.cms.bean.CustomerWeight;
import com.hongchao.cms.bean.MallOrder;
import com.hongchao.cms.service.mapper.CustomerMapper;
import com.hongchao.cms.util.ResponseEntity;
import com.hongchao.cms.util.SysApiStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by godlikehzj on 2017/2/18.
 */
@Service
public class CustomerService {
    @Autowired
    private CustomerMapper customerMapper;

    public List<CustomerInfo> getCustomerList(int statu){
        return customerMapper.getCustomerList(statu);
    }

    public List<CustomerAction> getCustomerActionById(long id){
        return customerMapper.getCustomerActionById(id);
    }

    public List<CustomerWeight> getCustomerWeightList(long id,String from, String to){
        return customerMapper.getCustomerWeightById(id, from, to);
    }

    public List<MallOrder> getMallOrders(long id,String from, String to){
        return customerMapper.getMallOrderByCustomer(id, from, to);
    }

    public Integer getTimes(long id, String from, String to){
        return customerMapper.getTimes(id, from, to);
    }

    public CustomerInfo getCustomerById(long id){
        return customerMapper.getCustomerById(id);
    }

    public ResponseEntity changeStatus(long id, int statu){
        if (statu == 1){
            statu = 0;
        }else{
            statu =1;
        }
        customerMapper.changeStatu(id, statu);
        return new ResponseEntity(SysApiStatus.OK, SysApiStatus.getMessage(SysApiStatus.OK), "");
    }

    public String getPic(String rid, int type){
        return customerMapper.getPic(rid, type);
    }
}
