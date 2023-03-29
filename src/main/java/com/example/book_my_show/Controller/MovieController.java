package com.example.book_my_show.Controller;

import org.springframework.data.util.Pair;
import com.example.book_my_show.EntryDto.MovieEntryDto;
import com.example.book_my_show.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    MovieService movieService;

    @PostMapping("/add")
    public ResponseEntity<String> addMovie(@RequestBody MovieEntryDto movieEntryDto){
        try{
            String result = movieService.addMovie(movieEntryDto);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }catch (Exception e){
            String response = "Movie not added";
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> removeMovie(@RequestParam("movieId") int movieId){
        String response = movieService.removeMovie(movieId);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping("/get-show-time")
    public ResponseEntity<List<Pair<LocalDate,LocalTime>>> getShowTime(@RequestParam("movie-id") int movieId,
                                                                       @RequestParam("theater-id") int theaterId){
        List<Pair<LocalDate, LocalTime>> pairList = movieService.getShowTime(movieId,theaterId);
        return new ResponseEntity<>(pairList,HttpStatus.FOUND);
    }


    }

