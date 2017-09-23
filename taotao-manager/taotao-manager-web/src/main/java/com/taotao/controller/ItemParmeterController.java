package com.taotao.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItemParam;
import com.taotao.service.ItemParmeterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/***
 * 商品参数Controller
 */
@Controller
@RequestMapping("/item/param")
public class ItemParmeterController {

    @Autowired
    private ItemParmeterService itemParmeterService;

    @RequestMapping("/query/itemcatid/{itemCatId}")
    @ResponseBody
    public TaotaoResult getItemParmByCid(@PathVariable long itemCatId){
        TaotaoResult result = itemParmeterService.getItemParmByCid(itemCatId);
        return result;
    }

    @RequestMapping("/save/{cid}")
    @ResponseBody
    public TaotaoResult createItemParm(@PathVariable long cid, String paramData){
        TbItemParam tbItemParam = new TbItemParam();
        tbItemParam.setItemCatId(cid);
        tbItemParam.setParamData(paramData);
        TaotaoResult result = itemParmeterService.insertItemParm(tbItemParam);
        return result;
    }
}
