package ru.geekbrains.demo.repository;

import org.springframework.stereotype.Repository;
import ru.geekbrains.demo.model.Book;
import ru.geekbrains.demo.model.Issue;
import ru.geekbrains.demo.model.Reader;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


// Классы, которые служат для доступа к базе данных или другим источникам данных.
// Она указывает, что класс является репозиторием, который предоставляет доступ к данным и
// выполняет операции базы данных, такие как сохранение, загрузка, обновление и удаление объектов
@Repository
public class IssueRepository {
    public final List<Issue> issues;

    public IssueRepository() {
        this.issues = new ArrayList<>();
    }

    public void save(Issue issue) {
        issues.add(issue);
    }
    public Issue getIssueById(long id){
        return issues.stream().filter(it -> Objects.equals(it.getId(), id))
                .findFirst()
                .orElse(null);
    }
    public List<Issue> getIssuesByReader(long id) {
        return issues
                .stream()
                .filter(x -> Objects.equals(x.getReaderId(), id))
                .toList();

    }

    public List<Issue> getAll() {
        return new ArrayList<>(issues);
    }

}