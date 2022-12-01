package ru.nshi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nshi.dto.AuditionsDTO;
import ru.nshi.exception.SongValidationException;
import ru.nshi.model.Song;
import ru.nshi.repository.SongRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SongServiceImpl implements SongService {

    @Autowired
    private SongRepository songRepository;

    @Override
    public Song save(Song song) {
        return songRepository.save(song);
    }

    @Override
    public Song getById(Long id) {
        return songRepository.getById(id);
    }

    @Override
    public Song updateById(Song song, Long id) {
        return songRepository.updateById(song, id);
    }

    @Override
    public Song deleteById(Long id) {
        return songRepository.deleteById(id);
    }

    @Override
    public List<Song> getSongList() {
        return songRepository.findAll();
    }

    @Override
    public List<Song> getSortedSongList(Integer limit) {
        return songRepository.findAll().stream()
                .sorted((o1, o2) -> o2.getAuditions() - o1.getAuditions())
                .limit(limit)
                .collect(Collectors.toList());
    }

    @Override
    public Song updateSongAuditionsById(Long id, AuditionsDTO auditionsDTO) {
        Song songToUpdate = songRepository.getById(id);

        if (auditionsDTO == null || auditionsDTO.getAuditions() < 1)
            throw new SongValidationException("Auditions must be greater than 0");

        songToUpdate.setAuditions(songToUpdate.getAuditions() + auditionsDTO.getAuditions());
        return songToUpdate;
    }

    @Override
    public List<Song> updateSongAuditionsByIds(AuditionsDTO auditionsDTO) {
        List<Song> resultList = new ArrayList<>();
        for (int i = 0; i < auditionsDTO.getSongs().length; i++) {
            resultList.add(updateSongAuditionsById(auditionsDTO.getSongs()[i], auditionsDTO));
        }

        return resultList;
    }
}
