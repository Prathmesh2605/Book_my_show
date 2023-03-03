package com.example.book_my_show.Services;

import com.example.book_my_show.Enums.*;
import com.example.book_my_show.EntryDto.TheatreEntryDto;
import com.example.book_my_show.Models.Theatre;
import com.example.book_my_show.Models.TheatreSeat;
import com.example.book_my_show.Repositories.*;
import com.example.book_my_show.convertors.TheatreConvertors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheatreService {
    @Autowired
    TheatreRepository theatreRepository;

    public String addTheater(TheatreEntryDto theatreEntryDto) throws Exception{

        Theatre  theatre= TheatreConvertors.convertDtoToEntity(theatreEntryDto);

        List<TheatreSeat> theaterSeatEntityList = createTheatreSeats(theatreEntryDto,theatre);
        theatre.setTheatreSeatList(theaterSeatEntityList);
        theatreRepository.save(theatre);


        return "Theatre added successfully";
    }
    private List<TheatreSeat> createTheatreSeats(TheatreEntryDto theatreEntryDto,Theatre theatre){


        int noClassicSeats = theatreEntryDto.getClassicSeatsCount();
        int noPremiumSeats = theatreEntryDto.getPremiumSeatsCount();

        List<TheatreSeat> theatreSeatList = new ArrayList<>();


        //Created the classic Seats
        for(int count = 1;count<=noClassicSeats;count++){

            //We need to make a new TheaterSeatEntity
            TheatreSeat theaterSeatEntity = TheatreSeat.builder()
                    .seatType(SeatType.CLASSIC).seatNo(count+"C")
                    .theatre(theatre).build();

            theatreSeatList.add(theaterSeatEntity);
        }


        //Create the premium Seats
        for(int count=1;count<=noPremiumSeats;count++){

            TheatreSeat theaterSeatEntity = TheatreSeat.builder().
                    seatType(SeatType.PREMIUM).seatNo(count+"P").theatre(theatre).build();

            theatreSeatList.add(theaterSeatEntity);

        }



        return theatreSeatList;

    }
}
