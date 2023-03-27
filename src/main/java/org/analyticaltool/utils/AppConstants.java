package org.analyticaltool.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AppConstants {
    public static final int REPORT_FILE_MAX_RECORDS_NUMBER = 100000;
    public static final int WRONG_INPUT_DATA_EXIT_STATUS = -1;
    public static final int NORMAL_EXIT_STATUS = 0;
    public static final int ERROR_EXIT_STATUS = 1;

    public static final String INPUT_PATH_STOP_WORD = "quit";

    public static final int DATA_BLOCKS_IN_WAITING_TIMELINE_QUANTITY = 6;
    public static final int DATA_BLOCKS_IN_QUERY_QUANTITY = 5;
    public static final int DATA_IN_LINE_TYPE = 0;
    public static final int SERVICE_AND_SERVICE_VARIATION_ID_BLOCK = 1;
    public static final int QUESTION_TYPE_AND_ITS_SUBCATEGORIES_ID_BLOCK = 2;
    public static final int RESPONSE_TYPE_BLOCK = 3;
    public static final int DATE_BLOCK = 4;
    public static final int WAITING_TIME_BLOCK = 5;

    public static final String DATA_IN_LINE_DELIMITER = " ";
    public static final String CATEGORIES_DELIMITER = ".";
    public static final String DATE_DELIMITER = "-";
    public static final String SPECIAL_CHARACTER = "*";
    public static final String DATA_TYPE_WAITING_TIMELINE = "C";
    public static final String DATA_TYPE_QUERY = "D";
    public static final String FIRST_ANSWER_DATA_TYPE = "P";
    public static final String NEXT_ANSWER_DATA_TYPE = "N";

    public static final int SERVICE_ID_START_NUMBER = 1;
    public static final int SERVICE_ID_END_NUMBER = 10;
    public static final int SERVICE_VARIATION_ID_START_NUMBER = 1;
    public static final int SERVICE_VARIATION_ID_END_NUMBER = 3;
    public static final int QUESTION_TYPE_ID_START_NUMBER = 1;
    public static final int QUESTION_TYPE_ID_END_NUMBER = 10;
    public static final int QUESTION_CATEGORY_ID_START_NUMBER = 1;
    public static final int QUESTION_CATEGORY_ID_END_NUMBER = 20;
    public static final int QUESTION_SUBCATEGORY_ID_START_NUMBER = 1;
    public static final int QUESTION_SUBCATEGORY_ID_END_NUMBER = 5;

    public static final String  REGEX_VALIDATION_ERROR = "Regex validation error! Line ";
    public static final String  LINE_TYPE_VALIDATION_ERROR = "Line type validation error! Line ";
    public static final String  SERVICE_ID_VALIDATION_ERROR = "Service id or service variation "
            + "id number validation error! Line ";
    public static final String  QUESTION_TYPE_VALIDATION_ERROR = "Question type id or question "
            + "type subcategories id validation error! Line ";

    public static final LocalDate START_DATE = LocalDate.of(1970, 01, 01);
    public static final LocalDate END_DATE = LocalDate.now();
    public static final String  DATE_FORMAT = "dd.MM.yyyy";
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);


}
