package ru.geekbrains.demo.repository;


import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import ru.geekbrains.demo.model.Book;
import ru.geekbrains.demo.model.Reader;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


// Классы, которые служат для доступа к базе данных или другим источникам данных.
// Она указывает, что класс является репозиторием, который предоставляет доступ к данным и
// выполняет операции базы данных, такие как сохранение, загрузка, обновление и удаление объектов
@Repository
public class ReaderRepository {
    public final List<Reader> readers;

    public ReaderRepository() {
        this.readers = new ArrayList<>();
    }

    @PostConstruct
    public void genereteData() {
        readers.addAll(List.of(
                new Reader("Victoria"),
                new Reader("Javochir"),
                new Reader("Kristina"),
                new Reader("Ivan"),
                new Reader("Artem"),
                new Reader("David")
        ));
    }
    public Reader getReaderById(long id){
        return readers.stream().filter(it -> Objects.equals(it.getId(), id))
                .findFirst()
                .orElse(null);
    }

    public void save(Reader reader) {
        readers.add(reader);
    }

    public void deleteReaderById(long id){
        readers.remove(id);
    }

    public List<Reader> getAll() {
        return new ArrayList<>(readers);
    }

}