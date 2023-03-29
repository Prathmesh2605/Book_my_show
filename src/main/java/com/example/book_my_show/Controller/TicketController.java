package com.example.book_my_show.Controller;


import com.example.book_my_show.EntryDto.TicketEntryDto;
import com.example.book_my_show.ResponseDto.TicketDetailsResponseDto;
import com.example.book_my_show.Services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    TicketService ticketService;

    @PostMapping("/book")
    public String bookTicket(@RequestBody TicketEntryDto ticketEntryDto){


        try{
            String result = ticketService.addTicket(ticketEntryDto);
            return result;

        }catch (Exception e){

            return "";
        }


    }

   @GetMapping("/get-ticket-details")
    public ResponseEntity<TicketDetailsResponseDto> getDetails(@RequestParam("ticketId") int ticketId){
        TicketDetailsResponseDto ticketDetailsResponseDto = ticketService.getDetails(ticketId);
        return new ResponseEntity<>(ticketDetailsResponseDto, HttpStatus.FOUND);
    }

    @DeleteMapping("/cancel_ticket")
    public ResponseEntity<String> cancelTicket(@RequestParam("ticketId") int ticketId){
        String response = ticketService.cancelTicket(ticketId);
        return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
    }
}
