package com.taotao.service.impl;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/***
 * 商品分类列表
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private TbItemCatMapper itemCatMapper;

    @Override
    public List<EUTreeNode> getCatList(Long parentID) {

        //创建查询条件
        TbItemCatExample example = new TbItemCatExample();
        //设置查询条件
        TbItemCatExample.Criteria criteria = example.createCriteria();
        //根据Parentid进行查询
        criteria.andParentIdEqualTo(parentID);
        //返回子节点列表
        List<TbItemCat> tbItemCats = itemCatMapper.selectByExample(example);
        //把EUTreeNode转换成List
        List<EUTreeNode> list = new ArrayList<>();
        EUTreeNode treeNode;
        for (TbItemCat tbItemCat: tbItemCats) {
            treeNode = new EUTreeNode();
            treeNode.setId(tbItemCat.getId());
            treeNode.setState(tbItemCat.getIsParent()?"closed":"open");
            treeNode.setText(tbItemCat.getName());
            list.add(treeNode);
        }
        return list;
    }
}
