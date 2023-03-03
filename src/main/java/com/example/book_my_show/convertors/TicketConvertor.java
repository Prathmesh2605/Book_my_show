package com.example.book_my_show.convertors;

import com.example.book_my_show.EntryDto.TicketEntryDto;
import com.example.book_my_show.Models.Ticket;

public class TicketConvertor {
    public static Ticket convertEntryToEntity(TicketEntryDto ticketEntryDto){

        Ticket Ticket = new Ticket();
        return Ticket;

    }
}
