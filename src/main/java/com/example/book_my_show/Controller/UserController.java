package com.example.book_my_show.Controller;


import com.example.book_my_show.EntryDto.UserEntryDto;
import com.example.book_my_show.Models.User;
import com.example.book_my_show.ResponseDto.TicketDetailsResponseDto;
import com.example.book_my_show.Services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;
    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody UserEntryDto userEntryDto){
        try {
            String response  = userService.addUser(userEntryDto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch (Exception e){
            String result = "User could not be add";
        }
        return new ResponseEntity<>("Not found",HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> removeUser(@RequestParam("userId") int userId){
        String response = userService.removeUser(userId);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping("/all-tickets")
    public ResponseEntity<List<TicketDetailsResponseDto>> allTickets(@RequestParam("userId") int userId){
        //This will return all tickets booked by user till now...and this includes even cancelled tickets also
        List<TicketDetailsResponseDto> ticketDetailsResponseDtoList = userService.allTickets(userId);
        return new ResponseEntity<>(ticketDetailsResponseDtoList,HttpStatus.FOUND);
    }
}
