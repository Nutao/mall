package com.taotao.common.pojo;

import java.util.List;


/***
 * EasyUI的DataGrid返回结果的Pojo Model
 * DataGrid数据格式
 * {total:”2”,rows:[{“id”:”1”,”name”,”张三”},{“id”:”2”,”name”,”李四”}]}
 */
public class EUDataGridResult {
    private long total;  //商品的总计录
    private List<?> rows;  //具体的商品信息

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}
