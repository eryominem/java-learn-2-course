package ru.nshi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
public class AuditionsDTO {
    @Min(1)
    private int auditions;

    @Length(min = 1)
    @JsonProperty(required = false)
    private long[] songs;
}
