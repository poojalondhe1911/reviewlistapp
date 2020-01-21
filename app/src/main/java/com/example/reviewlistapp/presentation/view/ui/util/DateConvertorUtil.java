package com.example.reviewlistapp.presentation.view.ui.util;

import org.jetbrains.annotations.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by  on ${15/02/18}.
 */
public class DateConvertorUtil {

    private static Date getDateFromString(SimpleDateFormat format, String dateStr) {

        Date date = null;
        try {
            date = format.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    public static String getDate(@Nullable String created) {
        SimpleDateFormat formatter = new SimpleDateFormat("E, dd MMM yyyy",Locale.ENGLISH);
        SimpleDateFormat formatter1 = parse(created);
        assert formatter1 != null;
        Date date = getDateFromString(formatter1, created);
        String strDate = formatter.format(date);
        System.out.println("Date Format with E, dd MMM yyy " + strDate);
        return strDate;
    }

    private static final String[] formats = {
            "yyyy-MM-dd'T'HH:mm:ss'Z'", "yyyy-MM-dd'T'HH:mm:ssZ",
            "yyyy-MM-dd'T'HH:mm:ss", "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
            "yyyy-MM-dd'T'HH:mm:ss.SSSZ", "yyyy-MM-dd HH:mm:ss",
            "MM/dd/yyyy HH:mm:ss", "MM/dd/yyyy'T'HH:mm:ss.SSS'Z'",
            "MM/dd/yyyy'T'HH:mm:ss.SSSZ", "MM/dd/yyyy'T'HH:mm:ss.SSS",
            "MM/dd/yyyy'T'HH:mm:ssZ", "MM/dd/yyyy'T'HH:mm:ss",
            "yyyy:MM:dd HH:mm:ss", "yyyyMMdd",};

    private static SimpleDateFormat parse(String d) {
        if (d != null) {
            for (String parse : formats) {
                Locale locale;
                SimpleDateFormat sdf = new SimpleDateFormat(parse, Locale.ENGLISH);
                try {
                    sdf.parse(d);
                    return sdf;
                } catch (ParseException ignored) {

                }
            }
        }
        return null;
    }
}
