package ru.geekbrains.demo.api;

import lombok.Data;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import ru.geekbrains.demo.JUnitSpringBootBase;
import ru.geekbrains.demo.model.Book;
import ru.geekbrains.demo.model.Issue;
import ru.geekbrains.demo.model.Reader;
import ru.geekbrains.demo.repository.BookRepository;
import ru.geekbrains.demo.repository.IssueRepository;
import ru.geekbrains.demo.repository.ReaderRepository;
import ru.geekbrains.demo.service.IssuerService;

import java.time.LocalDate;

class IssuerControllerTest extends JUnitSpringBootBase {

    @Autowired
    WebTestClient webTestClient;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    ReaderRepository readerRepository;
    @Autowired
    IssueRepository issueRepository;
    @Autowired
    IssuerService issuesService;


    @Data
    static class JUnitIssue {
        private Long id;
        private Long bookId;
        private Long readerId;
        private LocalDate issuedAt;
        private LocalDate timeReturn;

        public JUnitIssue(Long bookId, Long readerId) {
            this.bookId = bookId;
            this.readerId = readerId;
        }
    }

    @Test
    @DisplayName("Получение выдачи по ID")
    void getIssueById() {
        Book book = bookRepository.save(new Book("hello"));
        Reader reader = readerRepository.save(new Reader("Anna"));
        Issue requiredIssue = issuesService.saveIssue(new Issue(book.getId(), reader.getId()));

        JUnitIssue responseBody = webTestClient.get()
                .uri("/issue/" + requiredIssue.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(JUnitIssue.class)
                .returnResult().getResponseBody();

        Assertions.assertNotNull(responseBody);
        Assertions.assertEquals(requiredIssue.getId(), responseBody.getId());
        Assertions.assertEquals(requiredIssue.getBookId(), responseBody.getBookId());
        Assertions.assertEquals(requiredIssue.getReaderId(), responseBody.getReaderId());
        Assertions.assertNull(responseBody.getTimeReturn());
    }

    @Test
    void getIssueByIdNotFound() {
        webTestClient.get().uri("/issues/" + Long.MAX_VALUE)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testSaveIssue() {
        Book book = bookRepository.save(new Book("Java"));
        Reader reader = readerRepository.save(new Reader("Artem"));
        JUnitIssue savedIssue = new JUnitIssue(book.getId(), reader.getId());

        JUnitIssue responseBody = webTestClient.post()
                .uri("/issues")
                .bodyValue(savedIssue)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(JUnitIssue.class)
                .returnResult().getResponseBody();

        Assertions.assertNotNull(responseBody);
        Assertions.assertNotNull(responseBody.getId());
        Assertions.assertEquals(responseBody.getBookId(), savedIssue.getBookId());
        Assertions.assertEquals(responseBody.getReaderId(), savedIssue.getReaderId());
        Assertions.assertTrue(issueRepository.findById(responseBody.getId()).isPresent());
    }

    @Test
    void testDeleteById() {
        Book book = bookRepository.save(new Book("Java"));
        Reader reader = readerRepository.save(new Reader("Artem"));
        Issue deletedIssue = issuesService.saveIssue(new Issue(book.getId(), reader.getId()));

        webTestClient.delete()
                .uri("/issues/" + deletedIssue.getId())
                .exchange()
                .expectStatus().isNoContent();

        Assertions.assertFalse(issueRepository.findById(deletedIssue.getId()).isPresent());
    }

    @Test
    void testUpdateIssue() {
        Book book = bookRepository.save(new Book("Java"));
        Reader reader = readerRepository.save(new Reader("Artem"));
        Issue sIssue = issuesService.saveIssue(new Issue(book.getId(), reader.getId()));
        JUnitIssue updatedIssue = new JUnitIssue(book.getId(), reader.getId());


        JUnitIssue responseBody = webTestClient.put()
                .uri("/issue/" + sIssue.getId())
                .bodyValue(updatedIssue)
                .exchange()
                .expectStatus().isOk()
                .expectBody(JUnitIssue.class)
                .returnResult().getResponseBody();

        Assertions.assertNotNull(responseBody);
        Assertions.assertEquals(responseBody.getId(), sIssue.getId());
        Assertions.assertEquals(responseBody.getBookId(), sIssue.getBookId());
        Assertions.assertEquals(responseBody.getReaderId(), sIssue.getReaderId());
        Assertions.assertEquals(responseBody.getTimeReturn(), LocalDate.now());
        Assertions.assertTrue(issueRepository.findById(responseBody.getId()).isPresent());
    }


}
