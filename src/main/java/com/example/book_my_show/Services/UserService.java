package com.example.book_my_show.Services;

import com.example.book_my_show.EntryDto.UserEntryDto;
import com.example.book_my_show.Models.Show;
import com.example.book_my_show.Models.ShowSeat;
import com.example.book_my_show.Models.Ticket;
import com.example.book_my_show.Models.User;
import com.example.book_my_show.Repositories.ShowRepository;
import com.example.book_my_show.Repositories.UserRepository;
import com.example.book_my_show.ResponseDto.TicketDetailsResponseDto;
import com.example.book_my_show.convertors.TicketConvertor;
import com.example.book_my_show.convertors.UserConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ShowRepository showRepository;

    public String addUser(UserEntryDto userEntryDto)throws Exception, NullPointerException{

        User user = UserConvertor.convertDtoToEntity(userEntryDto);

        userRepository.save(user);
        return "User added successfully";
    }
    public String removeUser(int userId){
        User userEntity = userRepository.findById(userId).get();
        List<Ticket> ticketEntityList = userEntity.getTicketList();
        for(Ticket ticketEntity : ticketEntityList){
            String bookedSeats = ticketEntity.getBookedSeats();
            String [] bookedSeatsArr = bookedSeats.split(", ");
            if(ticketEntity.getShowTime().compareTo(LocalTime.now())<0){
                continue;
            }
            Show showEntity = ticketEntity.getShow();
            List<ShowSeat> showSeatEntityList = showEntity.getListOfShowSeats();
            for(ShowSeat showSeatEntity : showSeatEntityList){
                String seatNo = showSeatEntity.getSeatNo();
                if(Arrays.asList(bookedSeatsArr).contains(seatNo)){
                    showSeatEntity.setBooked(false);
                }
            }
            showRepository.save(showEntity);
        }
        userRepository.deleteById(userId);
        return "User deleted successfully";
    }

    public List<TicketDetailsResponseDto> allTickets(int userId){
        User userEntity = userRepository.findById(userId).get();
        List<Ticket> ticketEntityList = userEntity.getTicketList();
        List<TicketDetailsResponseDto> ticketDetailsResponseDtoList = new ArrayList<>();
        for(Ticket ticketEntity : ticketEntityList){
            TicketDetailsResponseDto ticketDetailsResponseDto = TicketConvertor.convertEntityToDto(ticketEntity);
            ticketDetailsResponseDtoList.add(ticketDetailsResponseDto);
        }
        return ticketDetailsResponseDtoList;
    }

    public String updateUserAddress(int userId, String address){
        User userEntity = userRepository.findById(userId).get();
        userEntity.setAddress(address);
        userRepository.save(userEntity);
        return "User address updated successfully";
    }
}
