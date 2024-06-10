package ru.geekbrains.demo.api;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.demo.model.Book;
import ru.geekbrains.demo.model.Issue;
import ru.geekbrains.demo.service.BookService;

import java.awt.*;
import java.util.NoSuchElementException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    private static final Logger log = LoggerFactory.getLogger(IssueController.class);
    @Autowired
    private BookService service;

    @GetMapping("/{id}")  //переменная часть адреса, будет подставляться значение id
    public ResponseEntity<Book> issueBoot(@PathVariable long id) {
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
    public ResponseEntity<Book> issueBoot(@RequestBody Book book){
        try {
            service.create(book);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Book> deleteBookById(@PathVariable long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

}
