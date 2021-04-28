package dao;

import org.apache.ibatis.annotations.Param;
import pojo.Books;

import java.util.List;

public interface BookMapper {
    //增加
    int addBook(Books books);

    //删除
    int deleteBookById(@Param("bookId") int id );

    //更新
    int updateBook(Books books);

    //查询
    Books queryBook(int id);

    //查询全部
    List<Books> queryAllBooks();
}
