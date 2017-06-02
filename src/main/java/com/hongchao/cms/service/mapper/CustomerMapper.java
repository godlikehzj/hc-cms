package com.hongchao.cms.service.mapper;

import com.hongchao.cms.bean.CustomerAction;
import com.hongchao.cms.bean.CustomerInfo;
import com.hongchao.cms.bean.CustomerWeight;
import com.hongchao.cms.bean.MallOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by godlikehzj on 2017/2/18.
 */
public interface CustomerMapper {
    public List<CustomerInfo> getCustomerList(@Param("statu") int statu);
    public CustomerInfo getCustomerById(@Param("id") long id);
    public void changeStatu(@Param("customerId") long customerId, @Param("statu") int statu);
    public List<CustomerAction> getCustomerActionById(@Param("id") long id);
    public List<CustomerWeight> getCustomerWeightById(@Param("id") long id, @Param("from") String from, @Param("to") String to);
    public String getPic(@Param("rid") String rid, @Param("type") int type);

    public Integer getTimes(@Param("id") long id, @Param("from") String from, @Param("to") String to);

    public List<MallOrder> getMallOrderByCustomer(@Param("id") long id, @Param("from") String from, @Param("to") String to);
}
