package com.example.book_my_show.Services;

import com.example.book_my_show.EntryDto.MovieEntryDto;
import com.example.book_my_show.Models.Movie;
import com.example.book_my_show.Repositories.MovieRepository;
import com.example.book_my_show.convertors.MovieConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;
    public String addMovie(MovieEntryDto movieEntryDto){
        Movie movie = MovieConvertor.convertEntryDtoToEntity(movieEntryDto);

        movieRepository.save(movie);
        return "Movie Added Successfully";

    }
}
