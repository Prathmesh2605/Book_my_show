package com.example.book_my_show.Services;

import com.example.book_my_show.EntryDto.MovieEntryDto;
import com.example.book_my_show.Models.Movie;
import com.example.book_my_show.Models.Show;
import com.example.book_my_show.Models.Theatre;
import com.example.book_my_show.Repositories.MovieRepository;
import com.example.book_my_show.convertors.MovieConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.util.Pair;
import java.time.LocalDate;
import java.util.*;
import java.time.LocalTime;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;
    public String addMovie(MovieEntryDto movieEntryDto){
        Movie movie = MovieConvertor.convertEntryDtoToEntity(movieEntryDto);

        movieRepository.save(movie);
        return "Movie Added Successfully";

    }

    public String removeMovie(int movieId){
        movieRepository.deleteById(movieId);
        return "Movie deleted successfully";
    }

    public List<Pair<LocalDate, LocalTime>> getShowTime(int movieId, int theaterId) {
        List<Pair<LocalDate, LocalTime>> pairList = new ArrayList<>();
        Movie movieEntity = movieRepository.findById(movieId).get();
        List<Show> showEntityList = movieEntity.getShowList();
        for(Show showEntity : showEntityList){
            Theatre theaterEntity = showEntity.getTheatre();
            if(theaterEntity.getId() == theaterId){
                LocalDate showDate = showEntity.getShowDate();
                LocalTime showTime = showEntity.getShowTime();
                Pair<LocalDate,LocalTime> pair = Pair.of(showDate,showTime);
                pairList.add(pair);
            }
        }
        return pairList;
    }
}
