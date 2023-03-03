package com.example.book_my_show.convertors;


import com.example.book_my_show.EntryDto.ShowEntryDto;
import com.example.book_my_show.Models.Show;

public class ShowConvertors {
    public static Show convertEntryToEntity(ShowEntryDto showEntryDto){

        Show show = Show.builder().showDate(showEntryDto.getLocalDate()).showTime(showEntryDto.getLocalTime()).showType(showEntryDto.getShowType()).build();

        return show;
    }

}
