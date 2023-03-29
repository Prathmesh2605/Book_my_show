package com.example.book_my_show.Controller;

import com.example.book_my_show.EntryDto.ShowEntryDto;
import com.example.book_my_show.Services.ShowService;
import com.example.book_my_show.convertors.ShowConvertors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/show")
public class ShowController {

    @Autowired
    ShowService showService;

    @PostMapping("/add")
    public ResponseEntity<String> addShow(@RequestBody ShowEntryDto showEntryDto){
        return new ResponseEntity<>(showService.addShow(showEntryDto), HttpStatus.CREATED);

    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> removeShow(@RequestParam("showId") int showId){
        String response = showService.removeShow(showId);
        if(response.equals("CANCELED")){
            //Email will be sent to the users that show has been cancelled,
            //you will be refunded your money back
        }
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

}
