package ru.geekbrains.demo.service;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.demo.api.IssueRequest;
import ru.geekbrains.demo.model.Book;
import ru.geekbrains.demo.model.Issue;
import ru.geekbrains.demo.repository.BookRepository;
import java.util.NoSuchElementException;



@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    public Book getById(long id) {
        if (bookRepository.getBookById(id) == null) {
            throw new NoSuchElementException("Не найдена книга с идентификатором \"" +  id + "\"");
        }
        Book book = bookRepository.getBookById(id);
        return book;
    }

    public void create(Book book) {
        bookRepository.save(book);
    }

    public void delete(long id) {
        bookRepository.deleteBookById(id);
    }
    public List<Book> getAll() { return bookRepository.getAll();}
}
