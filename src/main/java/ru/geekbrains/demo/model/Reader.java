package ru.geekbrains.demo.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Reader {

    public static long sequence = 1L;
    private final long id;
    private final String name;

    public Reader(String name) {
        this(sequence++, name);
    }
}
