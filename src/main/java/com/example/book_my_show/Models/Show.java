package com.example.book_my_show.Models;

import com.example.book_my_show.Enums.ShowType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.*;


@Data
@Entity
@Table(name = "shows")
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private LocalDate showDate;
    private LocalTime showTime;

    @Enumerated(value = EnumType.STRING)
    private ShowType showType;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date updateOn;

    //This is child wrt to the movieEntity
    @ManyToOne
    @JoinColumn
    private Movie movie;


    @ManyToOne
    @JoinColumn
    private Theatre theatre;


    //Show is parent wrt to ticket
    @OneToMany(mappedBy = "show",cascade = CascadeType.ALL)
    private List<Ticket> listOfBookedTickets = new ArrayList<>();

    @OneToMany(mappedBy = "show",cascade = CascadeType.ALL)
    private List<ShowSeat> listOfShowSeats = new ArrayList<>();
}
