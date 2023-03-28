package org.analyticaltool.utils.constants;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AppDateConstants {
    public static final int DATE_FULL_INFORMATION_SLOTS_QUANTITY = 2;
    public static final LocalDate START_DATE = LocalDate.of(1970, 01, 01);
    public static final LocalDate END_DATE = LocalDate.now();
    public static final String  DATE_FORMAT = "dd.MM.yyyy";
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);
}
