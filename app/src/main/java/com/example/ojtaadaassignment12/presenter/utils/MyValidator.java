package com.example.ojtaadaassignment12.presenter.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyValidator {
    public static boolean isDateValid(String date) {
        // Define the date format
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        sdf.setLenient(false); // Set lenient to false to strictly parse dates

        try {
            // Try to parse the date
            Date parsedDate = sdf.parse(date);
            return true; // If parsing is successful, the date is valid
        } catch (ParseException e) {
            return false; // If parsing fails, the date is invalid
        }
    }
}
