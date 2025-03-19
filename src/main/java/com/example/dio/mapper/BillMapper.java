package com.example.dio.mapper;

import com.example.dio.dto.response.BillResponse;
import com.example.dio.model.Bill;
import com.example.dio.model.Category;
import com.example.dio.model.CuisineType;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BillMapper {

    public BillResponse mapToBillResponse(Bill bill);

    default String mapToString(Category value) {
        if(value == null) {
            return null;
        }
        else return value.getCategory().toLowerCase();
    }

    default String mapToString(CuisineType cuisine) {
        if(cuisine == null) {
            return null;
        }
        else return cuisine.getCuisines().toLowerCase();
    }
}
