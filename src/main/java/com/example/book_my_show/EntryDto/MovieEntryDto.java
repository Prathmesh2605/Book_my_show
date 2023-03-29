package com.example.book_my_show.EntryDto;

import com.example.book_my_show.Enums.Genre;
import com.example.book_my_show.Enums.Language;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class MovieEntryDto {
    private String movieName;

    private double rating;
    private int duration;

    private Language language;

    private Genre genre;
}
