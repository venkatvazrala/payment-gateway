package com.payment.controller;

import com.payment.model.Invoice;
import com.payment.repository.InvoiceRepository;
import com.payment.response.PaymentResponse;
import com.payment.service.PaymentResponseFormatter;
import com.payment.validator.AttributeValidator;
import com.payment.validator.PaymentRequestValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping(path = "/api/v1/payment")
public class PaymentController {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    PaymentRequestValidator paymentRequestValidator;

    @Autowired
    private PaymentResponseFormatter paymentResponseFormatter;

    @Autowired
    AttributeValidator attributeValidator;

    private static final Logger logger = LogManager.getLogger(PaymentController.class);


    @PostMapping(path = "/validate",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Invoice> acceptPayment(@Valid @RequestBody Invoice newInvoice, Errors errors) throws Exception {

        logger.info("inside acceptPayment");
        attributeValidator.validate(newInvoice,errors);
        if (errors.hasErrors()) {

                Map<String, String> errorMap = new LinkedHashMap<>();
                errorMap.put("approve","false");
                 errors.getAllErrors().forEach( a -> {
                     errorMap.put(a.getCode().substring(0,a.getCode().indexOf("should")).trim(),a.getCode());
                 }
                 );
            return new ResponseEntity(errorMap, HttpStatus.BAD_REQUEST);
        }
        boolean isCardValid = paymentRequestValidator.validate(newInvoice.getCard().getPan());
        boolean isExpiryValid = paymentRequestValidator.validateExpiry(newInvoice.getCard().getExpiry());

        if(!isExpiryValid && isCardValid) {
            Invoice formattedInvoice = paymentRequestValidator.validate(newInvoice);
            invoiceRepository.save(formattedInvoice);
            PaymentResponse paymentResponse = new PaymentResponse();
            paymentResponse.setApprove("true");
            logger.info("invoice created "+formattedInvoice);
            return new ResponseEntity(paymentResponse, HttpStatus.CREATED);
        }else{
           return new ResponseEntity("Card details or not valid", HttpStatus.BAD_REQUEST);

        }

    }

    @GetMapping(path = "/transaction/{id}")
    public ResponseEntity<Invoice> getTransaction(@PathVariable long id) throws Exception {
        Optional<Invoice> invoice = invoiceRepository.findById(id);

        if (invoice.isPresent()) {
            Invoice formattedInvoice = paymentResponseFormatter.format(invoice.get());
            return new ResponseEntity(formattedInvoice, HttpStatus.OK);
        } else {
            return new ResponseEntity("couldn't find transaction", HttpStatus.BAD_REQUEST);
        }

    }
}
