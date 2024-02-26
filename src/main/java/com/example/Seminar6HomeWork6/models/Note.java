package com.example.Seminar6HomeWork6.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Класс заметки
 */
@Data
@Entity
@NoArgsConstructor
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // id заметки

    @Column(name = "heading", nullable = false)
    private String heading; // заголовок заметки

    @Column(name = "content",nullable = false)
    private String content; // содержимое заметки

    @Column(name = "dateTime")
    private LocalDateTime dateTime; // дата создания (автоматически устанавливается при создании заметки)

//    @ManyToOne
//    @JoinColumn(name = "note_id")
//    private Note note;
//
    public Note(String heading, String content) {
        this.heading = heading;
        this.content = content;
    }
}
