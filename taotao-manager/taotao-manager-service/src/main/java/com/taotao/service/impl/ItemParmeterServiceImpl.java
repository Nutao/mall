package com.taotao.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.service.ItemParmeterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/***
 * 商品规格参数模板
 */
@Service
public class ItemParmeterServiceImpl implements ItemParmeterService{

    @Autowired
    private TbItemParamMapper tbItemParamMapper;


    @Override
    public TaotaoResult getItemParmByCid(long cid) {

        TbItemParamExample example = new TbItemParamExample();
        TbItemParamExample.Criteria criteria = example.createCriteria();
        criteria.andItemCatIdEqualTo(cid);
        List<TbItemParam> tbItemParams = tbItemParamMapper.selectByExampleWithBLOBs(example);
//        判断是否查询到结果, 如果取到就返回取到的,如果没有则返回 200
        if (tbItemParams != null && tbItemParams.size() > 0){
            return TaotaoResult.ok(tbItemParams.get(0));
        }

        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult insertItemParm(TbItemParam tbItemParam) {
        // 补全POJO
        tbItemParam.setCreated(new Date());
        tbItemParam.setUpdated(new Date());
        // 插入到参数模板表 tb_item_para 中
        tbItemParamMapper.insert(tbItemParam);
        return TaotaoResult.ok();
    }
}
