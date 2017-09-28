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

}
