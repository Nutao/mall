package com.taotao.service;

import com.taotao.common.pojo.EUTreeNode;

import java.util.List;

/***
 * 商品类目Service接口
 */
public interface ItemCatService {
    public List<EUTreeNode> getCatList(Long parentID);
}
