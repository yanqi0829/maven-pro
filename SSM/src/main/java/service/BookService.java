package service;

import pojo.Books;

import java.util.List;

public interface BookService {
    int addBook(Books books);

    //删除
    int deleteBookById(int id);

    //更新
    int updateBook(Books books);

    //查询
    Books queryBook(int id);

    //查询全部
    List<Books> queryAllBooks();
}
