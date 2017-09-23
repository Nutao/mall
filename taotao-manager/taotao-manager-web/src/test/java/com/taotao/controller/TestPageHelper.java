package com.taotao.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.*;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Map;

public class TestPageHelper {

    @Test
    public void testPage(){
//        创建一个Spring容器
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
//        从Spring容器中获取Mapper的代理对象
        TbItemMapper itemMapper = context.getBean(TbItemMapper.class);
//        执行查询，并进行分页测试
        TbItemExample example = new TbItemExample();
//        分页
        PageHelper.startPage(1,10);
//        执行查询
        List<TbItem> list = itemMapper.selectByExample(example);
//        获取分页查询结果
        for (TbItem item : list) {
            System.out.println(item.getTitle());
        }
//        取分页信息
        PageInfo<TbItem> pageInfo = new PageInfo<>(list);
        System.out.println("DB中共有商品： "+ pageInfo.getTotal());

    }

    @Test
    public void testTbItemParamItem(){
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
        TbItemParamItemMapper itemParamItemMapper = context.getBean(TbItemParamItemMapper.class);
        TbItemParamItemExample example = new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(143771131488369L);
        //　执行查询
        List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
        if (null == list || list.size() == 0 ){
            System.out.println(" " );
        }
        // 获取查询结果
        TbItemParamItem tbItemParamItem = list.get(0);
        String paramData = tbItemParamItem.getParamData();
        // 将Json字符串转化为Java List对象(查看模板可知)
        List<Map> paramDataList = JsonUtils.jsonToList(paramData, Map.class);
        System.out.println(paramDataList);
        // 将其拼装成html
//        StringBuffer sb = new StringBuffer();
//
//        sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"1\" class=\"Ptable\">\n");
//        sb.append("    <tbody>\n");
//        for(Map m1:paramDataList) {
//            sb.append("        <tr>\n");
//            sb.append("            <th class=\"tdTitle\" colspan=\"2\">"+m1.get("group")+"</th>\n");
//            sb.append("        </tr>\n");
//            List<Map> list2 = (List<Map>) m1.get("params");
//            for(Map m2:list2) {
//                sb.append("        <tr>\n");
//                sb.append("            <td class=\"tdTitle\">"+m2.get("k")+"</td>\n");
//                sb.append("            <td>"+m2.get("v")+"</td>\n");
//                sb.append("        </tr>\n");
//            }
//        }
//        sb.append("    </tbody>\n");
//        sb.append("</table>");
//        System.out.println(sb.toString());
    }

}
