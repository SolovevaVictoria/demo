package ru.geekbrains.demo.api;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.demo.model.Book;
import ru.geekbrains.demo.model.Issue;
import ru.geekbrains.demo.service.IssuerService;
import ru.geekbrains.demo.api.IssueRequest;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequestMapping("/issue")
@NoArgsConstructor
@Tag(name = "Issue")
public class IssueController {
    @Autowired
    private IssuerService service;

    // создание запроса
    @PostMapping
    @Operation(summary = "add new issue", description = "Создание нового запроса на выдачу")
    public ResponseEntity<Issue> issueBook(@RequestBody Issue issue) {
        log.info("Получен запрос на выдачу: readerId = {}, bookId = {}", issue.getReaderId(), issue.getBookId());
        try {
            issue = service.saveIssue(issue);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(issue);
    }

    @GetMapping
    @Operation(summary = "get all issues", description = "Получение списка всех выдачей, которые есть в системе")
    public ResponseEntity<List<Issue>> getIssues() {
        return ResponseEntity.ok(service.getIssues());
    }

    @GetMapping("/{id}")
    @Operation(summary = "get issue by id", description = "Получение выдачи по идентификатору")
    public ResponseEntity<Issue> getIssueById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.getIssueById(id));
        } catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete issue by id", description = "Удаление выдачи по идентификатору")
    public ResponseEntity<Void> deleteIssue(@PathVariable Long id) {
        service.deleteIssue(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    @Operation(summary = "return book by id", description = "возврат книги по идентификатору")
    public ResponseEntity<Issue> returnBooks(@PathVariable Long id) {
        return ResponseEntity.ok(service.returnBooks(id));
    }
}

