package ru.geekbrains.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.demo.api.IssueRequest;
import ru.geekbrains.demo.model.Book;
import ru.geekbrains.demo.model.Issue;
import ru.geekbrains.demo.repository.BookRepository;
import ru.geekbrains.demo.repository.IssueRepository;
import ru.geekbrains.demo.repository.ReaderRepository;


import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class IssuerService {

    // спринг это всё заинжектит через конструктор
    private final BookRepository bookRepository;
    private final ReaderRepository readerRepository;
    private final IssueRepository issueRepository;

    public Issue issue(IssueRequest request) {
        if (bookRepository.getBookById(request.getBookId()) == null) {
            throw new NoSuchElementException("Не найдена книга с идентификатором \"" + request.getBookId() + "\"");
        }
        if (readerRepository.getReaderById(request.getReaderId()) == null) {
            throw new NoSuchElementException("Не найден читатель с идентификатором \"" + request.getReaderId() + "\"");
        }
        if (1 <= issueRepository.getIssuesByReader(request.getReaderId()).size()) {
            throw new RuntimeException("Читателю \"" + request.getReaderId() + "\" отказано в выдаче по причине " +
                    "максимального количества книг на руках. Необходимо осуществить предварительный возврат книг.");
        }
        Issue issue = new Issue(request.getBookId(), request.getReaderId());
        issueRepository.save(issue);
        return issue;
    }

    public Issue getById(long id) {
        if (issueRepository.getIssueById(id) == null) {
            throw new NoSuchElementException("Не найдена выдача \"" +  id + "\"");
        }
        Issue issue = issueRepository.getIssueById(id);
        return issue;
    }

    public List<Issue> getAll() { return issueRepository.getAll();}


}
