package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper tbContentMapper;

    /***
     * 获取内容信息, 根据id查询并分页
     * @param page
     * @param rows
     * @param categoryId
     * @return
     */
    @Override
    public EUDataGridResult getContentList(int page, int rows, long categoryId) {
//      分页信息
        PageHelper.startPage(page,rows);
//      执行查询
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        List<TbContent> list = tbContentMapper.selectByExample(example);
//      查询结果封装到DataGrid
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        result.setTotal(new PageInfo<TbContent>(list).getTotal());

        return result;
    }

    /***
     * 插入内容信息
     * @param content
     * @return
     */
    @Override
    public TaotaoResult inserContent(TbContent content) {

//        补全信息, 其他信息都是来自表单的Post内容
        content.setCreated(new Date());
        content.setUpdated(new Date());

        tbContentMapper.insert(content);
        return TaotaoResult.ok();
    }

    /***
     * 删除
     * @param id
     * @return
     */
    @Override
    public TaotaoResult deleteContent(long id) {
        tbContentMapper.deleteByPrimaryKey(id);
        return TaotaoResult.ok();
    }
}
