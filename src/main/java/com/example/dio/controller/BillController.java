package com.example.dio.controller;

import com.example.dio.dto.response.BillResponse;
import com.example.dio.service.BillService;
import com.example.dio.utility.ResponseBuilder;
import com.example.dio.utility.ResponseStructure;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("${app.base-url}")
public class BillController{

    private final BillService billService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/bills/tables/{tableId}")
    public ResponseEntity<ResponseStructure<BillResponse>> createBill(@PathVariable long tableId){
        BillResponse billResponse = billService.billGenerate(tableId);
        return ResponseBuilder.created(billResponse,"Bill Generated !!");
    }

    @GetMapping("/bills/{billId}")
    public ResponseEntity<ResponseStructure<BillResponse>> findByBillId(@PathVariable long billId){
        BillResponse billResponse = billService.findById(billId);
        return ResponseBuilder.ok(billResponse,"Bill Found !!");
    }

    @GetMapping("/bills/pdf/{billId}")
    public ResponseEntity<byte[]> findBillById(@PathVariable long billId) throws IOException {
        byte[] billPDF = billService.findBillById(billId);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=Dine-in-bill.pdf")
                .body(billPDF);
    }
}
