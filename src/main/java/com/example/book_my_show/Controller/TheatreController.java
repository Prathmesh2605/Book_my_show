package com.example.book_my_show.Controller;

import com.example.book_my_show.EntryDto.TheatreEntryDto;
import com.example.book_my_show.Services.TheatreService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/theatre")
public class TheatreController {

    @Autowired
    TheatreService theatreService;

    @PostMapping("/add")
    public ResponseEntity addTheatre(@RequestBody TheatreEntryDto theatreEntryDto){
        try{
            String result = theatreService.addTheater(theatreEntryDto);
            return new ResponseEntity(result, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> removeTheater(@RequestParam("theaterId") int theaterId){
        String response = theatreService.removeTheater(theaterId);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
