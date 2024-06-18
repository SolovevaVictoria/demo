package ru.geekbrains.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.demo.model.Book;
import ru.geekbrains.demo.model.Reader;
import ru.geekbrains.demo.repository.ReaderRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ReaderService {

    private final ReaderRepository readerRepository;

    @Autowired
    public ReaderService(ReaderRepository readerRepository) {
        this.readerRepository = readerRepository;
    }

    public Reader getById(Long id) {
        Optional<Reader> readerOptional = readerRepository.findById(id);
        return readerOptional.orElseThrow(() ->
                new NoSuchElementException("Не найден читатель с идентификатором \"" + id + "\""));
    }

    public Reader addReader(Reader reader) {
        return readerRepository.save(reader);
    }

    public void deleteReader(Long id) {
        readerRepository.deleteById(id);
    }

    public List<Reader> getAllReaders() {
        return readerRepository.findAll();
    }
}

//    private final ReaderRepository readerRepository;
//    public Reader getById(long id) {
//        if (readerRepository.getReaderById(id) == null) {
//            throw new NoSuchElementException("Не найден читатель с идентификатором \"" +  id + "\"");
//        }
//        Reader reader = readerRepository.getReaderById(id);
//        return reader;
//    }
//
//    public void create(Reader reader) {
//        readerRepository.save(reader);
//    }
//
//    public void delete(long id) {
//        readerRepository.deleteReaderById(id);
//    }
//
//    public List<Reader> getAll() {
//        return readerRepository.getAll();
//    }

