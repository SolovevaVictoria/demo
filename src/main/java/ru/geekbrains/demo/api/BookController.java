package ru.geekbrains.demo.api;
import java.util.List;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.demo.model.Book;
//import ru.geekbrains.demo.model.Issue;
import ru.geekbrains.demo.service.BookService;

import java.awt.*;
import java.util.NoSuchElementException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/book")
@Tag(name = "Book")
public class BookController {
    //private static final Logger log = LoggerFactory.getLogger(IssueController.class);

    private BookService service;
    @Autowired
    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping("/{id}")  //переменная часть адреса, будет подставляться значение id
    @Operation(summary = "get book by id", description = "Получение книги по идентификатору")
    public ResponseEntity<Book> issueBoot(@PathVariable Long id) {
        // ResponseEntity<> сущность HTTP-ответа, содержащая тело ответа и дополнительные метаданные, такие
        // как статус ответа и заголовки
        // метод контроллера возвращает объект типа ResponseEntity, который
        // содержит объект типа Book как тело ответа
        try {
            Book book = service.getById(id);
            return ResponseEntity.status(HttpStatus.CREATED).body(book);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(summary = "add new book", description = "Создание новой книги")
    public ResponseEntity<Book> issueBoot(@RequestBody Book book){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addBook(book));
    }

    @DeleteMapping({"/{id}"})
    @Operation(summary = "delete book by id", description = "Удаление книги по идентификатору")
    public ResponseEntity<Book> deleteBookById(@PathVariable Long id){
        service.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @Operation(summary = "get all books", description = "Загрузка всех книг, которые есть в системе")
    public ResponseEntity<List<Book>> getAll() {
        return ResponseEntity.ok(service.getAllBooks());
    }

}
