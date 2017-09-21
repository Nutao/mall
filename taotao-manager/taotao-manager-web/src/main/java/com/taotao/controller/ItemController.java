package com.taotao.controller;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/***
 * 商品管理Controller
 */

@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    /***
     * @RequestMapping 用于映射URL
     * @ResponseBody  使其返回Json对象
     * @PathVariable  映射参数
     * @param itemId
     * @return
     */
    @RequestMapping("/item/{itemId}")
    @ResponseBody
    public TbItem getItemByID(@PathVariable Long itemId){
        TbItem item = itemService.getItemByID(itemId);
        return item;
    }

    @RequestMapping("/item/list")
    @ResponseBody
    public EUDataGridResult getItemList(Integer page, Integer rows){
        EUDataGridResult itemList = itemService.getItemList(page, rows);
        return itemList;
    }

}