package ru.geekbrains.demo.api;

import lombok.Data;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.reactive.server.WebTestClient;
import ru.geekbrains.demo.model.Book;
import ru.geekbrains.demo.repository.BookRepository;

import java.util.List;
import java.util.stream.*;
import java.util.Objects;

public class BookControllerTest {

    @Autowired
    WebTestClient webTestClient;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Data
    static class JUnitBook {
        private Long id;
        private String name;
    }

    @Test
    void testSuccessFindById() {
        Book expected = bookRepository.save(new Book("Spring every day"));
        JUnitBook responceBody = webTestClient.get()
                .uri("/book/" + expected.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(JUnitBook.class)
                .returnResult().getResponseBody();
        Assertions.assertNotNull(responceBody);
        Assertions.assertEquals(expected.getId(), responceBody.getId());
        Assertions.assertEquals(expected.getName(), responceBody.getName());
    }


    @Test
    void testFindByIdNotFound() {
            webTestClient.get()
                .uri("/book/-1" )
                .exchange()
                .expectStatus().isNotFound();
    }




    @Test
    void testGetAll() {
        bookRepository.saveAll(List.of(new Book("springAll"), new Book("springMany")));
        List<Book> expected = bookRepository.findAll();

        List<JUnitBook> responseBody = webTestClient.get()
                .uri("/book")
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<List<JUnitBook>>() {})
                .returnResult()
                .getResponseBody();

        Assertions.assertNotNull(responseBody);
        Assertions.assertEquals(expected.size(), responseBody.size());
        for (JUnitBook book : responseBody) {
            boolean found = expected.stream()
                    .filter(exp -> Objects.equals(exp.getId(), book.getId()))
                    .anyMatch(exp -> Objects.equals(exp.getName(), book.getName()));
            Assertions.assertTrue(found);
        }
    }

    @Test
    void testSave() {
        JUnitBook newBook = new JUnitBook();
        newBook.setName("Java");

        JUnitBook responseBody = webTestClient.post()
                .uri("/book")
                .bodyValue(newBook)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(JUnitBook.class)
                .returnResult().getResponseBody();

        Assertions.assertNotNull(responseBody);
        Assertions.assertNotNull(responseBody.getId());
        Assertions.assertTrue(bookRepository.findById(responseBody.getId()).isPresent());
        Assertions.assertEquals(newBook.getName(), responseBody.getName());
    }

    @Test
    void testDeleteById() {
        Book deletedBook = bookRepository.save(new Book("Spring11111"));

        webTestClient.delete()
                .uri("/book/" + deletedBook.getId())
                .exchange()
                .expectStatus().isNoContent();

        Assertions.assertFalse(bookRepository.findById(deletedBook.getId()).isPresent());
    }

    @Test
    void testUpdateBook() {
        Book updatedBook = bookRepository.save(new Book("sprspr"));
        JUnitBook testUpdateBook = new JUnitBook();
        testUpdateBook.setName("Spring20000");

        JUnitBook bookRes = webTestClient.put()
                .uri("/book/" + updatedBook.getId())
                .bodyValue(testUpdateBook)
                .exchange()
                .expectStatus().isOk()
                .expectBody(JUnitBook.class)
                .returnResult().getResponseBody();

        Assertions.assertNotNull(bookRes);
        Assertions.assertEquals(updatedBook.getId(), bookRes.getId());
        Assertions.assertEquals(testUpdateBook.getName(), bookRes.getName());
    }

}
