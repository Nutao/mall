package com.taotao.service.impl;

import com.taotao.common.utils.FtpUtil;
import com.taotao.common.utils.IDUtils;
import com.taotao.service.PicService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/***
 * 图片上传Service
 */
@Service
public class PicServiceImpl implements PicService {
    @Value("${FTP_ADDRESS}")
    private String FTP_ADDRESS;

    @Value("${FTP_PORT}")
    private int FTP_PORT;

    @Value("${FTP_USERNAME}")
    private String FTP_USERNAME;

    @Value("${FTP_PASSWD}")
    private String FTP_PASSWD;

    @Value("${FTP_BASE_PATH}")
    private String FTP_BASE_PATH;

    @Value("${IMG_BASE_URL}")
    private String IMG_BASE_URL;





    @Override
    public Map uploadPicture(MultipartFile uploadFile) {

        Map map = new HashMap<>();
        //获取文件的原始名称
        String oldName = uploadFile.getOriginalFilename();
        //按照策略生成新的名称
        String newName = IDUtils.genImageName();
        newName = newName + oldName.substring(oldName.lastIndexOf("."));
        //上传
        String date = new DateTime().toString("/yyyy/MM/dd");
        try {
           boolean result = FtpUtil.uploadFile(FTP_ADDRESS,FTP_PORT,FTP_USERNAME,FTP_PASSWD,FTP_BASE_PATH,date,newName,uploadFile.getInputStream());
           if (!result){
               map.put("error",1);
               map.put("message","文件上传失败");
               return map;
           }
            map.put("error",0);
            map.put("url",IMG_BASE_URL+date+ File.separator+newName);
            return map;

        } catch (IOException e) {
            map.put("error",1);
            map.put("message","文件上传异常");
            return map;
        }
    }
}
