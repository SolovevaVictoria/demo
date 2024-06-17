//package ru.geekbrains.demo.model;
//
//import jakarta.persistence.*;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.RequiredArgsConstructor;
//
//@Data
//@Entity
//@Table(name = "readers")
//@NoArgsConstructor
//public class Reader {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name = "name")
//    private  String name;
//
//    public Reader(String name) {
//        this.name = name;
//    }
//
//    public Reader(Long id, String name) {
//        this.id = id;
//        this.name = name;
//    }
//
//    @Override
//    public String toString() {
//        return "Reader{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                '}';
//    }
//}
