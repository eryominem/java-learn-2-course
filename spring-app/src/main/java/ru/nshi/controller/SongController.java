package ru.nshi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import ru.nshi.dto.AuditionsDTO;
import ru.nshi.exception.SongNotFoundException;
import ru.nshi.exception.SongValidationException;
import ru.nshi.model.Song;
import ru.nshi.model.Error;
import ru.nshi.service.SongServiceImpl;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@Validated
public class SongController {
    private SongServiceImpl songService;

    @Autowired
    public SongController(SongServiceImpl songService) {
        this.songService = songService;
    }

    @GetMapping(path = "/ping", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> healthCheck() {
        return new ResponseEntity<>(Map.of("status", "UP"), HttpStatus.OK);
    }

    @GetMapping(path = "/songs", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Song> getSongs() {
        return songService.getSongList();
    }


    @PostMapping(path = "/songs", produces = MediaType.APPLICATION_JSON_VALUE)
    public Song createSong(@Valid @RequestBody Song song) {
        checkSong(song);
        return songService.save(song);
    }

    @GetMapping("/songs/{id}")
    public Song getSongById(@PathVariable(value = "id") Long id) {
        checkId(id);
        return songService.getById(id);
    }

    @PutMapping("/songs/{id}")
    public Song updateSongById(@Valid @RequestBody Song song, @PathVariable("id") Long id) {
        checkId(id);
        checkSong(song);
        return songService.updateById(song, id);
    }

    @DeleteMapping("/songs/{id}")
    public Song deleteSongById(@PathVariable("id") Long id) {
        checkId(id);
        return songService.deleteById(id);
    }

    @GetMapping(path = "/songs/listen", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Song> getSortedSongsByAuditions() {
        return songService.getSortedSongList();
    }

    @PutMapping(path = "/songs/listen", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Song> listenSongByIds(@RequestBody AuditionsDTO auditionsDTO) {
        return songService.updateSongAuditionsByIds(auditionsDTO);
    }

    @PutMapping(path = "/songs/listen/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Song listenSongById(@PathVariable("id") Long id, @RequestBody AuditionsDTO auditionsDTO) {
        return songService.updateSongAuditionsById(id, auditionsDTO);
    }

    @ExceptionHandler(SongValidationException.class)
    public ResponseEntity<Error> handleValidationException(SongValidationException ex) {
        Error error = new Error(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SongNotFoundException.class)
    public ResponseEntity<Error> handleNotFoundException(SongNotFoundException ex) {
        Error error = new Error(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Error> handleArgumentValidException(MethodArgumentNotValidException ex) {
        Error error = new Error("Arguments validation error");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    void checkId(Long id) {
        if (id == null) {
            throw new SongValidationException("Song message id cannot by null");
        }

        if (id < 1) {
            throw new SongValidationException("Song id cannot be less than 1");
        }
    }

    void checkSong(Song song) {
        if (song == null) {
            throw new SongValidationException("Song is null");
        }
    }
}
