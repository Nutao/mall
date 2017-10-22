package com.taotao.rest.service;

import com.taotao.pojo.TbContent;

import java.util.List;

/**
 * 内容服务接口
 */
public interface ContentService {
    List<TbContent> getContentList(long categoryId);
}
