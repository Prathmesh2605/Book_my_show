package com.example.book_my_show.convertors;

import com.example.book_my_show.EntryDto.UserEntryDto;
import com.example.book_my_show.Models.User;

public class UserConvertor {

    //static is kept to avoid calling it via objects/instances
    public static User convertDtoToEntity(UserEntryDto userEntryDto){
        User user = User.builder().age(userEntryDto.getAge()).address(userEntryDto.getAddress()).mobileNo(userEntryDto.getMobileNo()).name(userEntryDto.getName()).email(userEntryDto.getEmail()).build();

        return user;
        }
}
