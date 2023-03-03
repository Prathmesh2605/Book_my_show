package com.example.book_my_show.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "ticket")
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int price;
    private String movieName;

    private LocalTime showTime;
    private String ticketId = UUID.randomUUID().toString();
    private int totalAmount;

    private LocalDate showDate;
    private String theatreName;
    private String bookedSeats;
    @ManyToOne
    @JoinColumn
    private User user;

    //Ticket is child wrt to showEntity
    @ManyToOne
    @JoinColumn
    private Show show;



}
