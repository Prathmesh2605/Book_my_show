package com.example.book_my_show.convertors;

import com.example.book_my_show.EntryDto.MovieEntryDto;
import com.example.book_my_show.Models.Movie;

public class MovieConvertor {

    public static Movie convertEntryDtoToEntity(MovieEntryDto movieEntryDto){
        Movie movie = Movie.builder().movieName(movieEntryDto.getName()).duration(movieEntryDto.getDuration()).genre(movieEntryDto.getGenre()).rating(movieEntryDto.getRating()).language(movieEntryDto.getLanguage()).build();
        return movie;
     }
}
