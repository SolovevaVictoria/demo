package ru.geekbrains.demo.api;

import lombok.Data;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.web.reactive.server.WebTestClient;
import ru.geekbrains.demo.JUnitSpringBootBase;
import ru.geekbrains.demo.model.Reader;
import ru.geekbrains.demo.repository.ReaderRepository;


import java.util.List;
import java.util.Objects;

class ReaderControllerTest extends JUnitSpringBootBase {

    @Autowired
    WebTestClient webTestClient;
    @Autowired
    ReaderRepository readerRepository;

    @Data
    static class JUnitReader {
        private Long id;
        private String name;
    }

    @Test
    void testGetReaderById() {
        Reader testReader = readerRepository.save(new Reader ("Vika"));

        JUnitReader reader = webTestClient.get()
                .uri("/reader/" + testReader.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(JUnitReader.class)
                .returnResult().getResponseBody();
        Assertions.assertNotNull(reader);
        Assertions.assertEquals(testReader.getId(), testReader.getId());
        Assertions.assertEquals(testReader.getName(), testReader.getName());
    }

    @Test
    void testFindIdNotFound() {
       webTestClient.get()
                .uri("/readers/-1")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testGetAll() {
        readerRepository.saveAll(List.of(new Reader("Kris"),
                new Reader("Artem")));
        List<Reader> testReaders = readerRepository.findAll();

        List<JUnitReader> allReaders = webTestClient.get()
                .uri("/reader")
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<List<JUnitReader>>(){})
                .returnResult().getResponseBody();

        Assertions.assertNotNull(allReaders);
        Assertions.assertEquals(testReaders.size(), allReaders.size());
        for (JUnitReader reader : allReaders) {
            boolean found = testReaders.stream()
                    .filter(it -> Objects.equals(it.getId(), reader.getId()))
                    .anyMatch(it -> Objects.equals(it.getName(), reader.getName()));
            Assertions.assertTrue(found);
        }
    }

    @Test
    void testSaveReader() {
        JUnitReader testReader = new JUnitReader();
        testReader.setName("Anna");

        JUnitReader reader = webTestClient.post()
                .uri("/reader")
                .bodyValue(testReader)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(JUnitReader.class)
                .returnResult().getResponseBody();

        Assertions.assertNotNull(reader);
        Assertions.assertNotNull(reader.id);
        Assertions.assertTrue(readerRepository.findById(reader.getId()).isPresent());
        Assertions.assertEquals(testReader.getName(), reader.getName());
    }

    @Test
    void testDeleteByIdReader() {
        Reader deletedReader = readerRepository.save(new Reader("Vova"));

        webTestClient.delete()
                .uri("/reader/" + deletedReader.getId())
                .exchange()
                .expectStatus().isNoContent();

        Assertions.assertFalse(readerRepository.findById(deletedReader.getId()).isPresent());
    }

}