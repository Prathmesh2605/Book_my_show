package com.example.book_my_show.convertors;

import com.example.book_my_show.EntryDto.TheatreEntryDto;
import com.example.book_my_show.Models.Theatre;

public class TheatreConvertors {


    public static Theatre convertDtoToEntity(TheatreEntryDto theatreEntryDto){

        return Theatre.builder().location(theatreEntryDto.getLocation())
                .name(theatreEntryDto.getName()).build();

    }
}
