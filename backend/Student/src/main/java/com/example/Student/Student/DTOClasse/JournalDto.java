package com.example.Student.Student.DTOClasse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;


@JsonIgnoreProperties(ignoreUnknown = false)
@Data
public class JournalDto {
    private Integer id;
    private String title;
    private String content;
}
