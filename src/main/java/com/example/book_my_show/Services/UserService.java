package com.example.book_my_show.Services;

import com.example.book_my_show.EntryDto.UserEntryDto;
import com.example.book_my_show.Models.User;
import com.example.book_my_show.Repositories.UserRepository;
import com.example.book_my_show.convertors.UserConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    public String addUser(UserEntryDto userEntryDto)throws Exception, NullPointerException{

        User user = UserConvertor.convertDtoToEntity(userEntryDto);

        userRepository.save(user);
        return "User added successfully";
    }

}
