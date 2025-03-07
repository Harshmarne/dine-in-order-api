package com.example.dio.repositry;

import com.example.dio.model.CuisineType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CuisineTypeRepositry extends JpaRepository<CuisineType,String> {

}
