package ru.geekbrains.demo.api;

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

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/issue")
public class IssueController {
    @Autowired
    private IssuerService service;
    private static final Logger log = LoggerFactory.getLogger(IssueController.class);


    //Аннотация @PostMapping в Spring MVC указывает, что метод контроллера должен обрабатывать HTTP-запросы типа POST.
    // POST обычно используется для создания новых ресурсов на сервере или выполнения каких-либо действий,
    // которые могут изменить состояние сервера. Когда клиент отправляет POST-запрос на определенный URL, контроллер,
    // помеченный аннотацией @PostMapping, перехватывает этот запрос и выполняет определенную логику внутри метода
    // контроллера. Обычно этот метод выполняет операцию создания или обработки данных,
    // которые были переданы в теле запроса.
    @PostMapping
    public ResponseEntity<Issue> issueBoot(@RequestBody IssueRequest request) {
        log.info("Получен запрос на выдачу: readerId = {}, bookId = {}", request.getReaderId(), request.getBookId());

        final Issue issue;
        try {
            issue = service.issue(request);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(issue);
    }
    // (@PutMapping в контроллере Spring MVC указывает,
    // что метод контроллера должен обрабатывать HTTP-запросы типа PUT (обновление)
    // Когда клиент отправляет PUT-запрос на определенный URL, контроллер, помеченный аннотацией @PutMapping,
    // перехватывает этот запрос и выполняет определенную логику внутри метода контроллера

    @GetMapping("/{id}")  //переменная часть адреса, будет подставляться значение id
    public ResponseEntity<Issue> issueBoot(@PathVariable long id) {
        // ResponseEntity<> сущность HTTP-ответа, содержащая тело ответа и дополнительные метаданные, такие
        // как статус ответа и заголовки
        // метод контроллера возвращает объект типа ResponseEntity, который
        // содержит объект типа Book как тело ответа
        try {
            Issue issue = service.getById(id);
            return ResponseEntity.status(HttpStatus.CREATED).body(issue);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping
    public ResponseEntity<List<Issue>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

}
