//package ru.geekbrains.demo.repository;
//
//import jakarta.annotation.PostConstruct;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.stereotype.Repository;
//import org.springframework.web.bind.annotation.PutMapping;
//import ru.geekbrains.demo.model.Book;
//import ru.geekbrains.demo.model.Issue;
//import ru.geekbrains.demo.model.Reader;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//import java.util.Objects;
//
//import static com.fasterxml.jackson.databind.ser.std.NumberSerializers.addAll;
//
//
//// Классы, которые служат для доступа к базе данных или другим источникам данных.
//// Она указывает, что класс является репозиторием, который предоставляет доступ к данным и
//// выполняет операции базы данных, такие как сохранение, загрузка, обновление и удаление объектов
////@Repository
//public interface IssueRepository extends JpaRepository<Issue, Long> {
//    Collection<Object> findAllIssuesByReaderId(long readerId);
////    public final List<Issue> issues;
////
////    public IssueRepository() {
////        this.issues = new ArrayList<>();
////    }
////
////    public void save(Issue issue) {
////        issues.add(issue);
////    }
////    public Issue getIssueById(long id){
////        return issues.stream().filter(it -> Objects.equals(it.getId(), id))
////                .findFirst()
////                .orElse(null);
////    }
////
////    @PostConstruct
////    public void genereteData() {
////        issues.addAll(List.of(
////                new Issue(1, 1),
////                new Issue(2, 3),
////                new Issue(3, 2),
////                new Issue(4, 4)
////
////        ));
////    }
////
////
////    public List<Issue> getIssuesByReader(long id) {
////        return issues
////                .stream()
////                .filter(x -> Objects.equals(x.getReaderId(), id))
////                .toList();
////
////    }
////
////    public List<Issue> getAll() {
////        return new ArrayList<>(issues);
////    }
////    public Issue putById(long id) {
////        Issue issue = this.getIssueById(id);
////        issue.setTimeReturn(LocalDateTime.now());
////        return issue;
////    }
//
//}