//package ru.geekbrains.demo.api;
//
//
//import lombok.NoArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import ru.geekbrains.demo.model.Book;
//import ru.geekbrains.demo.model.Issue;
//import ru.geekbrains.demo.model.Reader;
//import ru.geekbrains.demo.service.BookService;
//import ru.geekbrains.demo.service.IssuerService;
//import ru.geekbrains.demo.service.ReaderService;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//@NoArgsConstructor
//@Controller
//public class ControllerUi {
////    @Autowired
////    private BookService serviceBook;
////    @Autowired
////    private ReaderService serviceReader;
////
////    @Autowired
////    private IssuerService issuerService;
////
////    @GetMapping("/ui/books")
////    public String listReaderss(Model model){
////        List<Book> items = serviceBook.getAll();
////        model.addAttribute("items", items);
////        return "books.html";  //расширение можно указывать / не указывать
////
////    }
////
////    @GetMapping("/ui/readers")
////    public String listReaders(Model model){
////        List<Reader> items = serviceReader.getAll();
////        model.addAttribute("items", items);
////        return "readers";
////    }
////
////    @GetMapping("/ui/issues")
////    public String issues(Model model) {
////        List<Issue> issues = issuerService.getAll();
////        model.addAttribute("issues", issues);
////        return  "issues";
////    }
//    }
