//package ru.geekbrains.demo.service;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import ru.geekbrains.demo.api.IssueRequest;
//import ru.geekbrains.demo.model.Book;
//import ru.geekbrains.demo.model.Issue;
//import ru.geekbrains.demo.repository.BookRepository;
//import ru.geekbrains.demo.repository.IssueRepository;
//import ru.geekbrains.demo.repository.ReaderRepository;
//
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.NoSuchElementException;
//
////@Service
//public class IssuerService {
//
//    // спринг это всё заинжектит через конструктор
//    private final BookRepository bookRepository;
//    private final ReaderRepository readerRepository;
//    private final IssueRepository issueRepository;
//
//
//    @Autowired
//    public IssuerService(BookRepository bookRepository, ReaderRepository readerRepository, IssueRepository issueRepository) {
//        this.bookRepository = bookRepository;
//        this.readerRepository = readerRepository;
//        this.issueRepository = issueRepository;
//    }
//
//    public Issue saveIssue(Issue issue) {
//        if (bookRepository.findById(issue.getBookId()).isEmpty()) {
//            throw new NoSuchElementException("Не найдена книга с идентификатором \"" + issue.getBookId() + "\"");
//        }
//        if (readerRepository.findById(issue.getReaderId()).isEmpty()) {
//            throw new NoSuchElementException("Не найден читатель с идентификатором \"" + issue.getReaderId() + "\"");
//        }
//        // можно проверить, что у читателя нет книг на руках (или его лимит не превышает в Х книг)
//
//        if (issueRepository.findAllIssuesByReaderId(issue.getReaderId()).size() >= 4) {
//            throw new RuntimeException("Читателю \"" + issue.getReaderId() + "\" отказано в выдаче по причине " +
//                    "максимального количества книг на руках. Необходимо осуществить предварительный возврат книг.");
//        }
//        Issue newissue = new Issue(issue.getBookId(), issue.getReaderId());
//        newissue.setTimestamp(LocalDateTime.now());
//        issueRepository.save(newissue);
//        return newissue;
//    }}
//
//
//
////    public Issue getById(long id) {
////        if (issueRepository.getIssueById(id) == null) {
////            throw new NoSuchElementException("Не найдена выдача \"" +  id + "\"");
////        }
////        Issue issue = issueRepository.getIssueById(id);
////        return issue;
////    }
////
////    public List<Issue> getAll() { return issueRepository.getAll();}
////
////    public Issue putById(long id) {
////        return issueRepository.putById(id);
////    }
//
