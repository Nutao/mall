package com.taotao.controller;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/***
 * 内容分类Controller
 */
@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {
    @Autowired
    private ContentCategoryService contentCategoryService;

    @RequestMapping("/list")
    @ResponseBody
    public List<EUTreeNode> getContentCategoryList(@RequestParam(value = "id", defaultValue = "0") long parentId){
        List<EUTreeNode> list = contentCategoryService.getContentCategoryList(parentId);
        return list;
    }

    @RequestMapping("/create")
    @ResponseBody
    public TaotaoResult createContentCategory(long parentId, String name){
        TaotaoResult result = contentCategoryService.insertContentCategory(parentId, name);
        return result;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public TaotaoResult deleteContentCategory(Long id){
        TaotaoResult result = contentCategoryService.deleteContentCategory(id);
        return result;
    }

    @RequestMapping("/update")
    @ResponseBody
    public TaotaoResult renameContentCategory(long id, String name){
        TaotaoResult result = contentCategoryService.renameContentCategory(id, name);
        return result;
    }


}
