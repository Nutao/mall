package com.taotao.controller;

import com.taotao.service.ItemParamItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/***
 * 商品参数Controller
 */
@Controller
public class ItemParamItemController {

    @Autowired
    private ItemParamItemService itemParamItemService;

    @RequestMapping("/itemParam/{itemID}")
    public String showItemParamItemParam(@PathVariable long itemID, Model model){

        String string = itemParamItemService.getItemParamItemByItemID(itemID);
        model.addAttribute("itemParam", string);
        return "item";
    }
}
