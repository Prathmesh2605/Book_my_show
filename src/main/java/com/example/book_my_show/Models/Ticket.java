package com.example.book_my_show.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@Entity
@Table(name = "ticket")
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int ticketId;
    int price;
    String movieName;
    LocalTime showTiming;

    String showDate;
    String theatreName;

    @ManyToOne
    @JoinColumn
    User user;

    //Ticket is child wrt to showEntity
    @ManyToOne
    @JoinColumn
    private Show show;



}
