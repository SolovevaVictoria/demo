package ru.geekbrains.demo.model;


import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Book {

    public static long sequence = 1L;
    private final long id;
    private final String name;

    public Book(String name) {
        this(sequence++, name);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
