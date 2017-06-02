package com.hongchao.cms.service;

import com.hongchao.cms.bean.MallCommodity;
import com.hongchao.cms.service.mapper.MallMapper;
import com.hongchao.cms.util.FileUtil;
import com.hongchao.cms.util.ResponseEntity;
import com.hongchao.cms.util.SysApiStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    public String uploadImg(HttpServletRequest request, String uploadPath){
        String filename=null;

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        try {
            CommonsMultipartFile orginalFile = (CommonsMultipartFile) multipartRequest.getFile("img");
            if (orginalFile != null && !orginalFile.isEmpty()) {
                String name = orginalFile.getOriginalFilename();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
                filename = formatter.format(new Date()) + name.substring(name.lastIndexOf("."));
                if (FileUtil.uploadFile(orginalFile.getInputStream(), uploadPath, filename)) {
                    System.out.println(filename);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return filename;
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
