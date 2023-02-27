package com.example.book_my_show.Repositories;

import com.example.book_my_show.Models.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface TheatreRepository extends CrudRepository<Theatre,Integer> {
}
