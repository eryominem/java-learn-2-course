package ru.nshi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Min;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@NoArgsConstructor
public class AuditionsDTO {
    @Min(1)
    private int auditions;

    @Length(min = 1)
    @JsonProperty(required = false)
    private long[] songs;
}
