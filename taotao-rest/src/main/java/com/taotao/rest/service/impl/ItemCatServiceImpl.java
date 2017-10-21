package com.taotao.rest.service.impl;

import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemParamItemExample;
import com.taotao.rest.pojo.CatNode;
import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/***
 * 商品分类服务
 */
@Service
public class ItemCatServiceImpl implements ItemCatService{
    @Autowired
    private TbItemCatMapper tbItemCatMapper;

    @Override
    public CatResult getItemCatList() {

        CatResult catResult = new CatResult();
//        查询分类列表
        catResult.setData(getCatList(0));
        return catResult;
    }

    private List<?> getCatList(long parentId) {
        //创建查询条件
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        //执行查询
        List<TbItemCat> list = tbItemCatMapper.selectByExample(example);
        //拼装JSON
        List resutList = new ArrayList<>();

        int count = 0;
        //向List中添加节点
        for (TbItemCat itemCat:list) {
            //父节点
            if (itemCat.getIsParent()){
                CatNode catNode = new CatNode();
                // 第一层要加 a 标签
                if (parentId == 0){
                    catNode.setName("<a href='/products/"+itemCat.getId()+".html'>"+itemCat.getName()+"</a>");
                }else {
                    catNode.setName(itemCat.getName());
                }
                catNode.setUrl("/products/"+itemCat.getId());
                catNode.setItem(getCatList(itemCat.getId()));  //递归
                resutList.add(catNode);

                count ++;
                //第一层只取14条记录
                if (parentId == 0 && count >= 14){
                    break;
                }
            }else{
                //叶子节点
                resutList.add("/products/"+itemCat.getId()+"|"+itemCat.getName());
            }
        }
        return resutList;
    }
}
