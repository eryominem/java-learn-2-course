package ru.nshi.repository;

import ru.nshi.model.Song;
import ru.nshi.exception.SongNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class SongRepositoryImpl implements SongRepository {
    private final Map<Long, Song> storage = new HashMap<>();
    private final AtomicInteger autoId = new AtomicInteger(0);

    @Override
    public Song save(Song song) {
        long id = autoId.incrementAndGet();
        song.setId(id);
        storage.put(id, song);

        return song;
    }

    @Override
    public Song getById(Long id) {
        Song result = storage.get(id);
        if (result == null)
            throw new SongNotFoundException("Song not found.");

        return result;
    }

    @Override
    public Song updateById(Song song, Long id) {
        Song songToUpdate = getById(id);
        song.setId(id);
        storage.put(id, song);

        return song;
    }

    @Override
    public Song deleteById(Long id) {
        Song result = storage.remove(id);
        if (result == null) {
            throw new SongNotFoundException("Song not found.");
        }

        return result;
    }

    @Override
    public List<Song> findAll() {
        return new ArrayList<>(storage.values());
    }
}
