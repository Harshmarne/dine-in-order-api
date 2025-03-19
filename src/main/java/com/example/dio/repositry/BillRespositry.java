package com.example.dio.repositry;

import com.example.dio.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRespositry extends JpaRepository<Bill,Long> {
}
