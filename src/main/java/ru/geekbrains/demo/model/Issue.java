package ru.geekbrains.demo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * Запись о факте выдачи книги (в БД)
 **/

@Data
@Entity
@Table(name = "issues")
@NoArgsConstructor
@Schema(name = "Выдачи")
public class Issue {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(name = "Идентификатор")
    @Id
    private Long id;

    @Column(name = "bookId")
    @Schema(name = "Идентификатор книги")
    private long bookId;

    @Column(name = "readerId")
    @Schema(name = "Идентификатор читателя")
    private long readerId;



    /**
    * Дата выдачи
    **/
    private LocalDateTime timestamp;
    /**
     * Дата возврата
     **/
    private LocalDateTime timeReturn = null;

    public Issue(long bookId, long readerId) {
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
