package com.taotao.service.impl;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService{

    @Autowired
    private TbContentCategoryMapper contentCategoryMapper;

    /***
     * 获取分类列表
     * @param parentId
     * @return
     */
    @Override
    public List<EUTreeNode> getContentCategoryList(long parentId) {
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);

        List<EUTreeNode> resultList = new ArrayList<>();
        for (TbContentCategory contentCategory: list) {
            EUTreeNode node = new EUTreeNode();
            node.setText(contentCategory.getName());
            node.setId(contentCategory.getId());
            node.setState(contentCategory.getIsParent()?"closed":"open");
            resultList.add(node);
        }
        return resultList;
    }

    /***
     * 增加分类节点
     * @param parentId
     * @param name
     * @return
     */
    @Override
    public TaotaoResult insertContentCategory(long parentId, String name) {
        //创建一个pojo
        TbContentCategory contentCategory = new TbContentCategory();
        contentCategory.setName(name);
        contentCategory.setIsParent(false);
        // 1 正常, 2 删除
        contentCategory.setStatus(1);
        contentCategory.setParentId(parentId);
        contentCategory.setSortOrder(1);
        contentCategory.setCreated(new Date());
        contentCategory.setUpdated(new Date());

        // 添加记录
        contentCategoryMapper.insert(contentCategory);
        // 查看父节点的isParent 是否为true, 如果不是则改为 true
        TbContentCategory category = contentCategoryMapper.selectByPrimaryKey(parentId);
        //判断是否为true
        if (!category.getIsParent()){
            category.setIsParent(true);
            category.setUpdated(new Date());
            //更新节点
            contentCategoryMapper.updateByPrimaryKey(category);
        }
        return TaotaoResult.ok(contentCategory);
    }

    /***
     * 递归删除节点
     * @param id
     * @return
     */
    @Override
    public TaotaoResult deleteContentCategory(Long id) {
        deleteCategoryAndChildNode(id);
        return TaotaoResult.ok();
    }

    private List<TbContentCategory> getChildList(Long id){
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(id);
        List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
        return list;
    }


    private void deleteCategoryAndChildNode(Long id) {
        // 获取要删除的category
        TbContentCategory contentCategory = contentCategoryMapper.selectByPrimaryKey(id);
        //判断是否为父节点
        if (contentCategory.getIsParent()){
            //获取所有孩子节点
            List<TbContentCategory> list = getChildList(id);
            //递归删除所有的孩子
            for (TbContentCategory tbContentCategory: list) {
                deleteCategoryAndChildNode(tbContentCategory.getId());
            }
        }

        //判断该节点的父节点下是否还有子节点
        if (getChildList(contentCategory.getParentId()).size() == 1){
            TbContentCategory category = contentCategoryMapper.selectByPrimaryKey(contentCategory.getParentId());
            category.setIsParent(false);
            contentCategoryMapper.updateByPrimaryKey(category);
        }
        // 删除节点
        contentCategoryMapper.deleteByPrimaryKey(id);
    }

    /***
     * 重命名节点
     * @param id
     * @param name
     * @return
     */
    @Override
    public TaotaoResult renameContentCategory(Long id, String name) {
        TbContentCategory contentCategory = contentCategoryMapper.selectByPrimaryKey(id);
        contentCategory.setName(name);
        contentCategoryMapper.updateByPrimaryKey(contentCategory);
        return TaotaoResult.ok();
    }


}
