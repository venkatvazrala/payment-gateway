package com.payment.controller;

import com.payment.model.Invoice;
import com.payment.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/payment")
public class PaymentController {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @PostMapping(path = "/transaction",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Invoice> acceptPayment(@RequestBody Invoice newInvoice) throws Exception {
        Invoice invoice = invoiceRepository.save(newInvoice);
        if (invoice == null) {
            throw new Exception();
        } else {
            return new ResponseEntity<>(invoice, HttpStatus.CREATED);
        }
    }

    @GetMapping("/audit")
    public String getTransaction(){

        return "Hello Payment";

    }
}
