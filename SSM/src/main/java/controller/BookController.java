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

    //查询全部书籍，并返回到一个书籍展示页面
    @RequestMapping("/allBooks")
    public String list(Model model) {
        List<Books> bookList = bookService.queryAllBooks();
        model.addAttribute("list", bookList);
        return "allBook";
    }

    //跳转到增加书籍页面
    @RequestMapping("/toAddBook")
    public String toAddPaper() {
        return "addBook";
    }

    //添加书籍的请求
    @RequestMapping("/addBook")
    public String toAddBook(Books books) {
        bookService.addBook(books);
        return "redirect:/book/allBooks";
    }

    //跳转到更新书籍页面
    @RequestMapping("/toUpdate")
    public String toUpdatePaper(int id, Model model) {
        Books books = bookService.queryBook(id);
        model.addAttribute("books", books);
        return "updateBook";
    }

    //更新书籍请求
    @RequestMapping("/updateBook")
    public String updateBook(Books books) {
        int i = bookService.updateBook(books);
        System.out.println("更新书籍" + books);
        return "redirect:/book/allBooks";
    }

    //删除书籍请求
    @RequestMapping("/deleteBook")
    public String deleteBook(int id) {
        int i = bookService.deleteBookById(id);
        System.out.println("删除书籍" + id);
        return "redirect:/book/allBooks";
    }
}
