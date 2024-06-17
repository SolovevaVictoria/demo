//package ru.geekbrains.demo.api;
//
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import ru.geekbrains.demo.model.Book;
////import ru.geekbrains.demo.model.Issue;
//import ru.geekbrains.demo.model.Reader;
//import ru.geekbrains.demo.service.BookService;
//import ru.geekbrains.demo.service.ReaderService;
//
//import java.util.NoSuchElementException;
//
//@RestController
//@RequestMapping("/reader")
//public class ReaderController {
//   // private static final Logger log = LoggerFactory.getLogger(IssueController.class);
//    @Autowired
//    private ReaderService service;
//
//    @GetMapping("/{id}")  //переменная часть адреса, будет подставляться значение id
//    public ResponseEntity<Reader> issueBoot(@PathVariable Long id) {
//        // ResponseEntity<> сущность HTTP-ответа, содержащая тело ответа и дополнительные метаданные, такие
//        // как статус ответа и заголовки
//        // метод контроллера возвращает объект типа ResponseEntity, который
//        // содержит объект типа Book как тело ответа
//        try {
//            Reader reader = service.getById(id);
//            return ResponseEntity.status(HttpStatus.CREATED).body(reader);
//        } catch (NoSuchElementException e) {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @PostMapping
//    public ResponseEntity<Reader> issueBoot(@RequestBody Reader reader){
//        try {
//            service.addReader(reader);
//        } catch (NoSuchElementException e) {
//            return ResponseEntity.notFound().build();
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.status(HttpStatus.CONFLICT).build();
//        }
//        return ResponseEntity.status(HttpStatus.CREATED).body(reader);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Reader> deleteReaderById(@PathVariable Long id){
//        service.deleteReader(id);
//        return ResponseEntity.noContent().build();
//    }
//
//}