package ru.geekbrains.demo.repository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import ru.geekbrains.demo.model.Book;
import ru.geekbrains.demo.model.Issue;

import java.util.Objects;

import java.util.ArrayList;
import java.util.List;


// Классы, которые служат для доступа к базе данных или другим источникам данных.
// Она указывает, что класс является репозиторием, который предоставляет доступ к данным и
// выполняет операции базы данных, такие как сохранение, загрузка, обновление и удаление объектов
@Repository
public class BookRepository {
    public final List<Book> books;

    public BookRepository() {
        this.books = new ArrayList<>();
    }

    @PostConstruct
    public void genereteData() {
        books.addAll(List.of(
                new Book("Евгений Онегин"),
                new Book("Spring быстро"),
                new Book("Spring в действии"),
                new Book("чистый код")
        ));
    }
    public Book getBookById(long id){
        return books.stream().filter(it -> Objects.equals(it.getId(), id))
                .findFirst()
                .orElse(null);
    }

    public void save(Book book) {
        books.add(book);
    }

    public void deleteBookById(long id){
        books.remove(id);
    }

    public List<Book> getAll() {
        return new ArrayList<>(books);
    }
    }
