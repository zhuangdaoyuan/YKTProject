package com.nesql;


import java.sql.ResultSet;

// 规范所有得数据库操作
public interface IBaseDao<T> {

    // 插入数据
    long insert(T bean);

    ResultSet select(int id);

    ResultSet selectAll();

}
