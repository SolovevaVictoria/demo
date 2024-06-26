package ru.geekbrains.demo.repository;


import jakarta.annotation.PostConstruct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
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
public interface ReaderRepository extends JpaRepository<Reader, Long> {

}