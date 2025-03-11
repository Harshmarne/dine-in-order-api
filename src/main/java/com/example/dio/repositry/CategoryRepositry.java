package com.example.dio.repositry;

import com.example.dio.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepositry extends JpaRepository<Category,String> {

}
