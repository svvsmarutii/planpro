package com.leftlane.planproparser.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateUtils {

    private static final SimpleDateFormat sdf1 = new SimpleDateFormat(Constants.DATE_FORMAT_1);
    private static final SimpleDateFormat sdf2 = new SimpleDateFormat(Constants.DATE_FORMAT_2);

    public static Date getDate1(String strDate) throws ParseException {
        return strDate != null && !(strDate.trim()).isEmpty() ? sdf1.parse(strDate.trim()) : null;
    }

    public static Date getDate2(String strDate) throws ParseException {
        return strDate != null && !(strDate.trim()).isEmpty() ? sdf2.parse(strDate.trim()) : null;
    }

}
