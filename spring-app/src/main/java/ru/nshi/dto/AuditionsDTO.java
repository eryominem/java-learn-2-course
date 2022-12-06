package ru.nshi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@NoArgsConstructor
public class AuditionsDTO {
    private int auditions;

    @JsonProperty(required = false)
    private long[] songs;
}
