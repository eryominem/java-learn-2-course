package ru.nshi.service;

import ru.nshi.dto.AuditionsDTO;
import ru.nshi.model.Song;

import java.util.List;

public interface SongService {
    Song save(Song song);
    Song getById(Long id);
    Song updateById(Song song, Long id);
    Song updateSongAuditionsById(Long id, AuditionsDTO auditionsDTO);
    Song deleteById(Long id);

    List<Song> getSongList();
    List<Song> getSortedSongList();
    List<Song> updateSongAuditionsByIds(AuditionsDTO auditionsDTO);

}
