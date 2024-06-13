package ru.geekbrains.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


/**
 * Запись о факте выдачи книги (в БД)
 **/
@Data
public class Issue {

    private final long id;
    private final long bookId;
    private final long readerId;
    public static long sequence = 1L;

    /**
    * Дата выдачи
    **/
    private final LocalDateTime timestamp;
    /**
     * Дата возврата
     **/
    private LocalDateTime timeReturn = null;

    public Issue(long bookId, long readerId) {
        this.id = sequence++;
        this.bookId = bookId;
        this.readerId = readerId;
        this.timestamp = LocalDateTime.now();

    }

    @Override
    public String toString() {
        return "Issue{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", readerId=" + readerId +
                ", timestamp=" + timestamp +
                '}';
    }
}
