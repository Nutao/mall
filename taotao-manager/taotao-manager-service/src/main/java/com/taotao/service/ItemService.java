package com.taotao.service;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.pojo.TbItem;

public interface ItemService {
    TbItem getItemByID(long itemID);

    EUDataGridResult getItemList(int page, int row);
}
