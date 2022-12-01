package ru.nshi.model;

import lombok.*;

@Data
@NoArgsConstructor
public class Song {
    private long id;
    private String artistName;
    private String name;
    private int auditions;
}
