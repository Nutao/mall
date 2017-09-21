package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/***
 * 商品管理Service
 */
@Service
public class ItemServiceImpl implements ItemService {

    //注入TbItem的代理对象TbItemMapper
    @Autowired
    private TbItemMapper itemMapper;

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
}
