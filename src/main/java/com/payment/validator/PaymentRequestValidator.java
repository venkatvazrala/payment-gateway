package com.payment.validator;

import com.payment.model.Invoice;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Base64;

@Component
public class PaymentRequestValidator {

    public Invoice validate(Invoice invoice) {

        String card = invoice.getCard().getPan();
        String expiry = invoice.getCard().getExpiry();
        String name = invoice.getCardHolder().getName();

        invoice.getCard().setPan(encode(card));
        invoice.getCard().setExpiry(encode(expiry));
        invoice.getCardHolder().setName(encode(name));
        return invoice;
    }

    public boolean validate(String card) {
        return validateCreditCardNumber(card);
    }

    public boolean validateExpiry(String expiry) {

        int year = Integer.valueOf("20".concat(expiry.substring(2, 4)));
        int month = Integer.valueOf(expiry.substring(0, 2));
        LocalDate currentDate = LocalDate.now();
        LocalDate expiryDate = LocalDate.of(year, month, currentDate.getDayOfMonth());


        if (expiryDate.isBefore(currentDate)) {
            return true;
        }
        return false;

    }


    private String encode(String input) {
        return Base64.getEncoder().encodeToString(input.getBytes());
    }

    private boolean validateCreditCardNumber(String str) {

        int[] ints = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
            ints[i] = Integer.parseInt(str.substring(i, i + 1));
        }
        for (int i = ints.length - 2; i >= 0; i = i - 2) {
            int j = ints[i];
            j = j * 2;
            if (j > 9) {
                j = j % 10 + 1;
            }
            ints[i] = j;
        }
        int sum = 0;
        for (int i = 0; i < ints.length; i++) {
            sum += ints[i];
        }
        if (sum % 10 == 0) {
            return true;
        } else {
            return false;
        }
    }


}
