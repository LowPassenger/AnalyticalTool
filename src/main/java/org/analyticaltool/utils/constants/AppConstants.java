package org.analyticaltool.utils.constants;

import java.time.format.DateTimeFormatter;

public class AppConstants {
    public static final int REPORT_FILE_MAX_RECORDS_NUMBER = 100000;
    public static final int WRONG_INPUT_OUTPUT_DATA_EXIT_STATUS = -1;
    public static final int NORMAL_EXIT_STATUS = 0;
    public static final int ERROR_EXIT_STATUS = 1;
    public static final String INPUT_PATH_STOP_WORD = "quit";
    public static final String OUTPUT_REPORT_DIRECTORY = "./reports/";
    public static final String OUTPUT_FILE_TEMPLATE_NAME = "report";
    public static final String LOCAL_DATE_TIME_PATTERN_FOR_OUTPUT_REPORT_FILE
            = "dd-MM-yyyy-HH:mm:ss";
    public static final DateTimeFormatter FORMATTER_FOR_OUTPUT_FILE = DateTimeFormatter
            .ofPattern(LOCAL_DATE_TIME_PATTERN_FOR_OUTPUT_REPORT_FILE);
    public static final String OUTPUT_FILE_EXTENTION = ".txt";
    public static final String REPORT_FILE_DOESNT_MATCH_SYMBOL = "-";
}
