package com.hongchao.cms.util;

import com.hongchao.cms.bean.FixContent;
import com.hongchao.cms.service.mapper.HouseMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by godlikehzj on 2017/1/26.
 */
public class Config {
    public static String imgPath = "/opt/www/files/mall";
    public static String imgUrl = "http://www.bjhongchaohuanbao.com/files/mall/";
    private static Map<Integer, String> fixContents = new HashMap<>();

    public static Map<Integer, String> getFixContents(){
        if (fixContents.size() == 0){
            HouseMapper houseMapper = (HouseMapper)SpringContext.getBean("houseMapper");
            List<FixContent> list = houseMapper.getFixContents();
            for(FixContent fixContent : list){
                fixContents.put((int) fixContent.getId(), fixContent.getContent());
            }
        }

        return fixContents;
    }
}
