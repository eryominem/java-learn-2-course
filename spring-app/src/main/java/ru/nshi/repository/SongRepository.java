package ru.nshi.repository;

import ru.nshi.model.Song;

import java.util.List;

public interface SongRepository {
    Song save(Song song);
    Song getById(Long id);
    Song updateById(Song song, Long id);
    Song deleteById(Long id);
    List<Song> findAll();
}
