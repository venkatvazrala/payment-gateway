package com.payment.service;

import com.payment.model.Invoice;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class PaymentResponseFormatter {

    public Invoice format(Invoice invoice) throws Exception {
       String card = maskString(decode(invoice.getCard().getPan()), 0, 12, '*');
       String name = maskString(decode(invoice.getCardHolder().getName()), 0, invoice.getCardHolder().getName().length(), '*');
       String expiry = maskString(decode(invoice.getCard().getExpiry()), 0, 4, '*');

       invoice.getCard().setPan(card);
       invoice.getCard().setExpiry(expiry);
       invoice.getCardHolder().setName(name);
       return invoice;
    }

    private String decode(String input){
        byte[] decodedBytes = Base64.getDecoder().decode(input);
        String decodedString = new String(decodedBytes);
        return decodedString;
    }
    private static String maskString(String strText, int start, int end, char maskChar)
            throws Exception{

        if(strText == null || strText.equals(""))
            return "";

        if(start < 0)
            start = 0;

        if( end > strText.length() )
            end = strText.length();

        if(start > end)
            throw new Exception("End index cannot be greater than start index");

        int maskLength = end - start;

        if(maskLength == 0)
            return strText;

        String strMaskString = StringUtils.repeat(maskChar, maskLength);

        return StringUtils.overlay(strText, strMaskString, start, end);
    }

}
