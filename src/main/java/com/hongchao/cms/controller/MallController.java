package com.hongchao.cms.controller;

import com.hongchao.cms.bean.CustomerInfo;
import com.hongchao.cms.bean.MallCommodity;
import com.hongchao.cms.service.MallService;
import com.hongchao.cms.util.Config;
import com.hongchao.cms.util.ResponseEntity;
import com.hongchao.cms.util.SysApiStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by godlikehzj on 2017/5/31.
 */
@Controller
@RequestMapping("mall")
public class MallController extends BaseController{
    @Autowired
    private MallService mallService;

    @RequestMapping("list.do")
    public String getList(ModelMap modelMap, int statu){
        List<MallCommodity> commodities = mallService.getCommodityList(statu);
        modelMap.addAttribute("commodities", commodities);
        modelMap.addAttribute("status", statu);
        return "list.jsp";
    }

    @RequestMapping("changeStatu.do")
    public void changeCustomerStatu(HttpServletRequest request,
                                    HttpServletResponse response,
                                    Long commodityId, int statu){
        outResult(request, response, "json", mallService.changeStatus(commodityId, statu));
    }

    @RequestMapping("toAdd.do")
    public String toAddCommodity(){
        return "add.jsp";
    }

    @RequestMapping("add.do")
    public void addCommodity(HttpServletRequest request,
                             HttpServletResponse response,
                             String name,
                             String description,
                             Integer cost_point){
        MallCommodity commodity = new MallCommodity();
        commodity.setName(name);
        commodity.setDescription(description);
        commodity.setCost_point(cost_point);
        commodity.setImg("");
        commodity.setStatu(1);
        String imgUrl = mallService.uploadImg(request, Config.imgPath);
        if (imgUrl != null){
            commodity.setImg(imgUrl);
        }
        mallService.addCommodity(commodity);

        outResult(request, response, "json", new ResponseEntity(SysApiStatus.OK, SysApiStatus.getMessage(SysApiStatus.OK), ""));
    }

    @RequestMapping("toEdit.do")
    public String toEditCommodity(ModelMap modelMap, long id){
        MallCommodity commodity = mallService.getCommodityById(id);
        modelMap.addAttribute("commodity", commodity);

        return "edit.jsp";
    }

    @RequestMapping("edit.do")
    public void editCommodity(HttpServletRequest request,
                              HttpServletResponse response,
                              Long id,
                              String name,
                              String description,
                              Integer cost_point){
        MallCommodity commodity = mallService.getCommodityById(id);
        commodity.setName(name);
        commodity.setDescription(description);
        commodity.setCost_point(cost_point);
        commodity.setImg("");
        commodity.setStatu(1);
        String imgUrl = mallService.uploadImg(request, Config.imgPath);
        if (imgUrl != null){
            commodity.setImg(imgUrl);
        }
        mallService.addCommodity(commodity);
        outResult(request, response, "json", new ResponseEntity(SysApiStatus.OK, SysApiStatus.getMessage(SysApiStatus.OK), ""));
    }
}
