package ru.geekbrains.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.demo.model.Book;
import ru.geekbrains.demo.model.Reader;
import ru.geekbrains.demo.repository.BookRepository;
import ru.geekbrains.demo.repository.ReaderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ReaderService {
    private final ReaderRepository readerRepository;
    public Reader getById(long id) {
        if (readerRepository.getReaderById(id) == null) {
            throw new NoSuchElementException("Не найден читатель с идентификатором \"" +  id + "\"");
        }
        Reader reader = readerRepository.getReaderById(id);
        return reader;
    }

    public void create(Reader reader) {
        readerRepository.save(reader);
    }

    public void delete(long id) {
        readerRepository.deleteReaderById(id);
    }

    public List<Reader> getAll() {
        return readerRepository.getAll();
    }

}