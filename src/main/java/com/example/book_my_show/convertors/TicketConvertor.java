package com.example.book_my_show.convertors;

import com.example.book_my_show.EntryDto.TicketEntryDto;
import com.example.book_my_show.Models.Ticket;
import com.example.book_my_show.ResponseDto.TicketDetailsResponseDto;

public class TicketConvertor {
    public static Ticket convertEntryToEntity(TicketEntryDto ticketEntryDto){

        //Ticket ticket = Ticket.builder().id(ticketEntryDto.getUserId()).ticketId(ticketEntryDto.getShowId()).s
        Ticket ticket = new Ticket();
        return ticket;

    }
    public static TicketDetailsResponseDto convertEntityToDto(Ticket ticketEntity){
        return TicketDetailsResponseDto.builder()
                .id(ticketEntity.getId())
                .ticketId(ticketEntity.getTicketId())
                .theaterName(ticketEntity.getTheatreName())
                .totalAmount(ticketEntity.getTotalAmount())
                .movieName(ticketEntity.getMovieName())
                .showEntityId(ticketEntity.getShow().getId())
                .bookedSeats(ticketEntity.getBookedSeats())
                .showDate(ticketEntity.getShowDate())
                .showTime(ticketEntity.getShowTime())
                .userEntityId(ticketEntity.getUser().getId())
                .status(ticketEntity.getStatus())
                .build();
    }
}
