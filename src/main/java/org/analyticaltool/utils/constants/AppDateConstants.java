package org.analyticaltool.utils.constants;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AppDateConstants {
    public static final int DATE_FULL_INFORMATION_SLOTS_QUANTITY = 2;
    public static final int QUERY_START_DATE_SLOT_NUMBER = 0;
    public static final int QUERY_END_DATE_SLOT_NUMBER = 1;
    public static final LocalDate START_DATE = LocalDate.of(1970, 1, 1);
    public static final LocalDate END_DATE = LocalDate.now();
    public static final String DATE_FORMAT = "d.MM.yyyy";
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);
}
