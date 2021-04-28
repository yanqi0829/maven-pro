package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pojo.Books;
import service.BookService;

import java.awt.print.Book;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    @Qualifier("bookServiceImpl")
    private BookService bookService;

    @RequestMapping("/allBooks")
    public String list(Model model) {
        List<Books> bookList = bookService.queryAllBooks();
        model.addAttribute("list", bookList);
        return "allBook";
    }

    //跳转到增加书籍页面
    @RequestMapping("/toAddBook")
    public String toAddPage() {
        return "addBook";
    }

    //添加书籍的请求
    @RequestMapping("/addBook")
    public String toAddBook(Books books) {
        bookService.addBook(books);
        return "redirect:/book/allBooks";
    }

}
