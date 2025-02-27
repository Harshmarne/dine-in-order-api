package com.example.dio.repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.dio.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositry extends JpaRepository<User,Long> {
}
