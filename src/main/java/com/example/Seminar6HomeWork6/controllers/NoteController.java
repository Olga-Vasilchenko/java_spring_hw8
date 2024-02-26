package com.example.Seminar6HomeWork6.controllers;

import com.example.Seminar6HomeWork6.models.Note;
import com.example.Seminar6HomeWork6.services.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Класс - контроллер, используется для обработки запросов
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/notes")
public class NoteController {

    private final NoteService noteService;

    /**
     * Метод обработки Get-запроса без параметров по адресу - localhost:8080/notes
     *
     * @return - список всех заметок
     */
    @GetMapping
    public ResponseEntity<List<Note>> getAll() {
        return new ResponseEntity<>(noteService.getAllNotes(), HttpStatus.OK);
    }

    /**
     * Метод обработки Post-запроса с добавлением параметров заметки по адресу - localhost:8080/notes
     *
     * @param note - новая заметка, передается через body
     * @return - новая заметка
     */
    @PostMapping
    public ResponseEntity<Note> createNote(@RequestBody Note note) {
        return new ResponseEntity<>(noteService.createNote(note), HttpStatus.CREATED);
    }


    /**
     * Метод обработки Get-запроса списка заметок с указанным статусом по адресу - localhost:8080/notes/{id}
     * @param id - id заметки
     * @return
     */
    @GetMapping("{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable("id") Long id) {
        Note note;
        try {
            note = noteService.getNoteById(id);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Note());
        }
        return new ResponseEntity<>(note, HttpStatus.OK);
    }

    /**
     * Метод обработки Put-запроса на изменение заметки по id
     *
     * @param id      - id заметки
     * @param heading - заголовок заметки
     * @param content - содержимое заметки
     * @return - возвращает измененную заметку
     */
    @PutMapping("{id}")
    @ResponseBody
    public ResponseEntity<Note> updateNote(@PathVariable("id") Long id,
                                           @RequestParam("heading") String heading,
                                           @RequestParam("content") String content) {
        return new ResponseEntity<>(noteService.updateNote(id, heading, content), HttpStatus.OK);
    }

    /**
     * Метод обработки Delete-запроса на удаление заметки по id по адресу - localhost:8080/notes/{id}
     * @param id - id заметки
     * @return - Void
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable("id") Long id) {
        noteService.deleteNote(id);
        return ResponseEntity.ok().build();
    }
}
