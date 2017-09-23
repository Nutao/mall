package com.taotao.service.impl;

import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemParamItemExample;
import com.taotao.service.ItemParamItemService;
import com.taotao.service.ItemParmeterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/***
 * 商品参数Service
 */
@Service
public class ItemParamItemServiceImpl implements ItemParamItemService {

    @Autowired
    private TbItemParamItemMapper itemParamItemMapper;

    @Override
    public String getItemParamItemByItemID(long itemID) {

        // 根据商品ID查询商品的规格参数
        TbItemParamItemExample example = new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(itemID);
        //　执行查询
        List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
        if (null == list || list.size() == 0 ){
            return "";
        }
        // 获取查询结果
        TbItemParamItem tbItemParamItem = list.get(0);
        String paramData = tbItemParamItem.getParamData();
        // 将Json字符串转化为Java List对象(查看模板可知)
        List<Map> paramDataList = JsonUtils.jsonToList(paramData, Map.class);
        // 将其拼装成html
        StringBuffer sb = new StringBuffer();

        sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"1\" class=\"Ptable\">\n");
        sb.append("    <tbody>\n");
        for(Map m1:paramDataList) {
            sb.append("        <tr>\n");
            sb.append("            <th class=\"tdTitle\" colspan=\"2\">"+m1.get("group")+"</th>\n");
            sb.append("        </tr>\n");
            List<Map> list2 = (List<Map>) m1.get("params");
            for(Map m2:list2) {
                sb.append("        <tr>\n");
                sb.append("            <td class=\"tdTitle\">"+m2.get("k")+"</td>\n");
                sb.append("            <td>"+m2.get("v")+"</td>\n");
                sb.append("        </tr>\n");
            }
        }
        sb.append("    </tbody>\n");
        sb.append("</table>");
        return sb.toString();
    }
}
