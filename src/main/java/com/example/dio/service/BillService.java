package com.example.dio.service;

import com.example.dio.dto.response.BillResponse;

import java.io.IOException;

public interface BillService {

    BillResponse billGenerate(long tableId);

    public BillResponse findById(long billId);

    byte[] findBillById(long billId) throws IOException;
}
