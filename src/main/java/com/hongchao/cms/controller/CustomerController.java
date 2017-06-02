package com.hongchao.cms.controller;

import com.hongchao.cms.bean.CustomerAction;
import com.hongchao.cms.bean.CustomerInfo;
import com.hongchao.cms.bean.CustomerWeight;
import com.hongchao.cms.bean.MallOrder;
import com.hongchao.cms.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by godlikehzj on 2017/2/18.
 */
@Controller
@RequestMapping("customer")
public class CustomerController extends BaseController{

    @Autowired
    private CustomerService customerService;

    @RequestMapping("list.do")
    public String getList(ModelMap modelMap, int statu){
        List<CustomerInfo> customerInfos = customerService.getCustomerList(statu);
        modelMap.addAttribute("customers", customerInfos);
        modelMap.addAttribute("status", statu);
        return "list.jsp";
    }

    @RequestMapping("getPic.do")
    public void getPic(ModelMap modelMap,
                       HttpServletResponse response,
                       String rid, int type) throws IOException{
        String url = customerService.getPic(rid, type);
        response.sendRedirect(url);
    }

    @RequestMapping("detail.do")
    public String getDetail(ModelMap modelMap, Long customerId, String from, String to){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date currentTime = new Date();
        String today = formatter.format(currentTime);

        if (from == null && to == null){
            from = today;
            to = today;
        }
        modelMap.addAttribute("from", from);
        modelMap.addAttribute("to", to);

        CustomerInfo customerInfo = customerService.getCustomerById(customerId);

        modelMap.addAttribute("customer",customerInfo);
        Integer times = customerService.getTimes(customerId, from, to);
        modelMap.addAttribute("times", times);

        List<CustomerWeight> weights = customerService.getCustomerWeightList(customerId, from, to);

        long wet = 0;
        long dry = 0;
        long total = 0;
        for(CustomerWeight weight:weights){
            if (weight.getCategory() == 0){
                total += weight.getWeight();
            }else if (weight.getCategory() == 1){
                weight.setUrl("customer/getPic.do?rid="+weight.getRid() + "&type=" + (weight.getCategory() - 1));
                dry += weight.getWeight();
            }else if (weight.getCategory() == 2){
                weight.setUrl("customer/getPic.do?rid="+weight.getRid() + "&type=" + (weight.getCategory() - 1));
                wet += weight.getWeight();
            }

        }
        modelMap.addAttribute("weights", weights);

        modelMap.addAttribute("total", total);
        modelMap.addAttribute("wet", wet);
        modelMap.addAttribute("dry", dry);

        List<MallOrder> mallOrders = customerService.getMallOrders(customerId, from, to);
        modelMap.put("mallOrders", mallOrders);
        int orderNum = 0;
        if (mallOrders != null){
            orderNum = mallOrders.size();
        }

        modelMap.put("orderNum", orderNum);

        return "detail.jsp";
    }

    @RequestMapping("changeStatu.do")
    public void changeCustomerStatu(HttpServletRequest request,
                                    HttpServletResponse response,
                                    Long customerId, int statu){
        outResult(request, response, "json", customerService.changeStatus(customerId, statu));
    }
}
