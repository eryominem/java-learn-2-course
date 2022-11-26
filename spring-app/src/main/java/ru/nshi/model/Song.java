package ru.nshi.model;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class Song {
    private long id;

    @NotBlank()
    private String artistName;

    @NotBlank
    private String name;

    @Min(value = 0)
    private int auditions;
}
