package com.taotao.controller;

import com.taotao.common.utils.JsonUtils;
import com.taotao.service.PicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/***
 * 上传图片处理
 */
@Controller
public class PictureController {

    @Autowired
    private PicService picService;

    @RequestMapping("/pic/upload")
    @ResponseBody
    public String pictureUpload(MultipartFile uploadFile){
        Map map = picService.uploadPicture(uploadFile);
//        为了保证兼容性,把Map转换为Json格式的String
        String result = JsonUtils.objectToJson(map);
        return result;
    }
}
