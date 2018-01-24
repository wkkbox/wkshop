package com.wk.wkshop.lucene.dao;

import com.wk.wkshop.lucene.po.Book;

import java.util.List;

public interface BookDao {
    /**
     * 采集数据：查询所有图书
     * @return
     */
    List<Book> listBooks();
}
