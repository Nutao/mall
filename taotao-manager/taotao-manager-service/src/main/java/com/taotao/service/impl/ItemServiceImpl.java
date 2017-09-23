package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.*;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/***
 * 商品管理Service
 */
@Service
public class ItemServiceImpl implements ItemService {

    //注入TbItem的代理对象TbItemMapper
    @Autowired
    private TbItemMapper itemMapper;

    @Autowired
    private TbItemDescMapper itemDescMapper;

    @Autowired
    private TbItemParamItemMapper tbItemParamItemMapper;

    /***
     * 根据查询商品信息
     * @param itemID
     * @return
     */
    @Override
    public TbItem getItemByID(long itemID) {
        TbItem item = itemMapper.selectByPrimaryKey(itemID);
        if (null != item){
            return item;
        }
        return null;
    }


    /***
     * 商品列表查询
     * @param page
     * @param row
     * @return
     */
    @Override
    public EUDataGridResult getItemList(int page, int row) {
        // 查询商品列表
        TbItemExample example = new TbItemExample();
        // 分页处理，根据传入的page和row来分
        PageHelper.startPage(page,row);
        List<TbItem> list = itemMapper.selectByExample(example);
        //创建一个EUDataGridResult接受Pagehelper的分页查询返回
        EUDataGridResult result = new EUDataGridResult();
//         取记录的具体信息
        result.setRows(list);
//        取记录的总条数
        result.setTotal(new PageInfo<TbItem>(list).getTotal());
        return result;
    }

    @Override
    public TaotaoResult creatItem(TbItem item, String desc, String itemParam) throws Exception {
        //item 补全
        // 生成商品ID
        long itemID = IDUtils.genItemId();
        // 设置ID
        item.setId(itemID);
        // 设置状态
        item.setStatus((byte)1);
        // 设置创建时间
        item.setCreated(new Date());
        item.setUpdated(new Date());
        // 插入到数据库
        itemMapper.insert(item);

        //添加商品描述
        TaotaoResult result = this.insertItemDesc(itemID,desc);
        if (result.getStatus() != 200){
            throw new Exception();
        }
        // 添加商品规格
        result = this.insertItemParamItem(itemID, itemParam);
        if (result.getStatus() != 200){
            throw new Exception();
        }

        return TaotaoResult.ok();
    }

    private TaotaoResult insertItemParamItem(long itemID, String itemParam) {
        //创建POJO
        TbItemParamItem tbItemParamItem = new TbItemParamItem();
        //补全信息
        tbItemParamItem.setItemId(itemID);
        tbItemParamItem.setParamData(itemParam);
        tbItemParamItem.setCreated(new Date());
        tbItemParamItem.setUpdated(new Date());
        // 插入到表中
        tbItemParamItemMapper.insert(tbItemParamItem);
        return TaotaoResult.ok();
    }

    private TaotaoResult insertItemDesc(long itemID, String desc) {
        // 创建一个描述对象, 并填充属性的内容
        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setItemId(itemID);
        tbItemDesc.setItemDesc(desc);
        tbItemDesc.setCreated(new Date());
        tbItemDesc.setUpdated(new Date());
        // 执行表的插入, 相当于一个SQL插入
        itemDescMapper.insert(tbItemDesc);

        return TaotaoResult.ok();
    }
}
