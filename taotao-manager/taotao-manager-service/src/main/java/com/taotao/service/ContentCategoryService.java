package com.taotao.service;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.TaotaoResult;

import java.util.List;

/***
 * 内容分类服务
 */
public interface ContentCategoryService {
    List<EUTreeNode> getContentCategoryList(long parentId);
    TaotaoResult insertContentCategory(long parentId, String name);
    TaotaoResult deleteContentCategory(Long id);
    TaotaoResult renameContentCategory(Long id, String name);
}
