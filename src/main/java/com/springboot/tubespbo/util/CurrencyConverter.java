package com.springboot.tubespbo.util;

import java.text.NumberFormat;
import java.util.Locale;

public class CurrencyConverter {
    public static String toIDR(double amount) {
        Locale indonesiaLocale = new Locale("id", "ID");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(indonesiaLocale);
        String formattedAmount = currencyFormatter.format(amount);
        return formattedAmount.replace(",00", "");
    }
}
