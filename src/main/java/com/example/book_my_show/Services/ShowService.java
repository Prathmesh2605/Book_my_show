package com.example.book_my_show.Services;

import com.example.book_my_show.Controller.ShowController;
import com.example.book_my_show.EntryDto.ShowEntryDto;
import com.example.book_my_show.Enums.SeatType;
import com.example.book_my_show.Models.*;
import com.example.book_my_show.Repositories.MovieRepository;
import com.example.book_my_show.Repositories.*;
import com.example.book_my_show.convertors.ShowConvertors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.book_my_show.convertors.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {


    @Autowired
    MovieRepository movieRepository;

    @Autowired
    TheatreRepository theaterRepository;

    @Autowired
    ShowRepository showRepository;

    public String addShow(ShowEntryDto showEntryDto)
    {
        //1. Create a Show
        Show show = ShowConvertors.convertEntryToEntity(showEntryDto);

        int movieId = showEntryDto.getMovieId();
        int theaterId = showEntryDto.getTheatreId();

        Movie movie = movieRepository.findById(movieId).get();
        Theatre Theatre = theaterRepository.findById(theaterId).get();


        //Setting the attribute of foreignKe
        show.setMovie(movie);
        show.setTheatre(Theatre);

        //Pending attributes the listOfShowSeatsEnity

        List<ShowSeat> seatList = createShowSeat(showEntryDto,show);

        show.setListOfShowSeats(seatList);


        //Now we  also need to update the parent entities


        show = showRepository.save(show);

        movie.getShowList().add(show);
        Theatre.getShowList().add(show);


        movieRepository.save(movie);

        theaterRepository.save(Theatre);

        return "The show has been added successfully";

    }

    private List<ShowSeat> createShowSeat(ShowEntryDto showEntryDto,Show Show){



        /*Now the goal is to create the showSeatEntity
         * and for that we need to set all the attributes of showSeatEntity
         *
         * Following are the attributes of showSeatEntity :-
         * isBooked
         * price
         * seatNo
         * seatType
         * bookedAt
         * showEntity
         *
         * */

        Theatre Theatre = Show.getTheatre();

        List<TheatreSeat> TheatreSeatList = Theatre.getTheatreSeatList();

        List<ShowSeat> seatEntityList = new ArrayList<>();

        for(TheatreSeat TheatreSeat : TheatreSeatList){

            ShowSeat ShowSeat = new ShowSeat();

            ShowSeat.setSeatNo(TheatreSeat.getSeatNo());
            ShowSeat.setSeatType(TheatreSeat.getSeatType());

            if(TheatreSeat.getSeatType().equals(SeatType.CLASSIC))
                ShowSeat.setPrice(showEntryDto.getClassicSeatPrice());

            else
                ShowSeat.setPrice(showEntryDto.getPremiumSeatPrice());

            ShowSeat.setBooked(false);
            ShowSeat.setShow(Show); //parent : foreign key for the showSeat Entity

            seatEntityList.add(ShowSeat); //Adding it to the list
        }

        return  seatEntityList;

    }

    public String removeShow(int showId){
        Show showEntity = showRepository.findById(showId).get();
        if(showEntity.getShowTime().compareTo(LocalTime.now())>0){
            return "CANCELED";
        }
        showRepository.deleteById(showId);
        return "REMOVED";
    }
}
