package com.taotao.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItemParam;

public interface ItemParmeterService {
    TaotaoResult getItemParmByCid(long cid);
    TaotaoResult insertItemParm(TbItemParam tbItemParam);
}
