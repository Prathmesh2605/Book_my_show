package com.example.book_my_show.Services;

import com.example.book_my_show.EntryDto.TicketEntryDto;
import com.example.book_my_show.Models.*;
import com.example.book_my_show.Repositories.*;
import com.example.book_my_show.convertors.TicketConvertor;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class TicketService {
    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    ShowRepository showRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JavaMailSender javaMailSender;

    public String addTicket(TicketEntryDto ticketEntryDto) throws Exception{


        //1. Create Ticket from entryDto : Convert DTO ---> Entity
        Ticket Ticket = TicketConvertor.convertEntryToEntity(ticketEntryDto);


        //Validation : Check if the requested seats are available or not ?
        boolean isValidRequest = checkValidityofRequestedSeats(ticketEntryDto);

        if(isValidRequest==false){
            throw new Exception("Requested seats are not available");
        }

        //We assume that the requestedSeats are valid


        //Calculate the total amount :
        Show show = showRepository.findById(ticketEntryDto.getShowId()).get();
        List<ShowSeat> seatEntityList = show.getListOfShowSeats();
        List<String> requestedSeats = ticketEntryDto.getRequestedSeats();

        int totalAmount = 0;
        for(ShowSeat ShowSeat:seatEntityList){

            if(requestedSeats.contains(ShowSeat.getSeatNo())){
                totalAmount = totalAmount + ShowSeat.getPrice();
                ShowSeat.setBooked(true);
                ShowSeat.setBookedAt(new Date());
            }
        }

        Ticket.setTotalAmount(totalAmount);


        //Setting the other attributes for the Ticket
        Ticket.setMovieName(show.getMovie().getMovieName());
        Ticket.setShowDate(show.getShowDate());
        Ticket.setShowTime(show.getShowTime());
        Ticket.setTheatreName(show.getTheatre().getName());


        //We need to set that string that talked about requested Seats
        String allotedSeats = getAllotedSeatsfromShowSeats(requestedSeats);
        Ticket.setBookedSeats(allotedSeats);


        //Setting the foreign key attributes
        User user = userRepository.findById(ticketEntryDto.getUserId()).get();

        Ticket.setUser(user);
        Ticket.setShow(show);

        //Save the parent
        Ticket = ticketRepository.save(Ticket);


        List<Ticket> TicketList = show.getListOfBookedTickets();
        TicketList.add(Ticket);
        show.setListOfBookedTickets(TicketList);

        showRepository.save(show);


        List<Ticket> TicketList1 = user.getBookedTickets();
        TicketList1.add(Ticket);
        user.setBookedTickets(TicketList1);

        userRepository.save(user);


        String body = "Hi this is to confirm your booking for seat No "+allotedSeats +"for the movie : " + Ticket.getMovieName();


        MimeMessage mimeMessage=javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage,true);
        mimeMessageHelper.setFrom("backeendacciojob@gmail.com");
        mimeMessageHelper.setTo(user.getEmail());
        mimeMessageHelper.setText(body);
        mimeMessageHelper.setSubject("Confirming your booked Ticket");

        javaMailSender.send(mimeMessage);


        return "Ticket has successfully been added";

    }

    private String getAllotedSeatsfromShowSeats(List<String> requestedSeats){

        String result = "";

        for(String seat :requestedSeats){

            result = result + seat +", ";

        }
        return result;

    }


    private boolean checkValidityofRequestedSeats(TicketEntryDto ticketEntryDto){

        int showId = ticketEntryDto.getShowId();

        List<String> requestedSeats = ticketEntryDto.getRequestedSeats();

        Show Show = showRepository.findById(showId).get();

        List<ShowSeat> listOfSeats = Show.getListOfShowSeats();

        //Iterating over the list Of Seats for that particular show
        for(ShowSeat ShowSeat : listOfSeats){

            String seatNo = ShowSeat.getSeatNo();

            if(requestedSeats.contains(seatNo)){

                if(ShowSeat.isBooked()==true){
                    return false; //Since this seat cant be occupied : returning false
                }
            }
        }
        //All the seats requested were available
        return true;

    }
}
