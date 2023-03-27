package org.analyticaltool.utils;

public class Regex {
    public static final String LINE_TYPE_REGEX = "[CD]";
    public static final String SERVICES_TYPE_REGEX =
            "[\\s]{1}(((([1-9][0]?)|[\\*]?))|((([1-9][0]?)|[\\*]?)[\\.]{1}([1-3]|\\*?)|\\*?))";
    public static final String QUESTION_TYPE_REGEX =
            "[\\s]{1}((([1-9][0]?)|\\*?)|((([1-9][0]?)|\\*?)[\\.]{1}(([1-9][\\d]?)|\\*?))|"
                    + "((((([1-9][0]?)|\\*?)[\\.]{1}(([1-9][\\d]?)|"
                    + "\\*?)))[\\.]{1}([1-5]|\\*?)|\\*?))";
    public static final String RESPONSE_TIME_TYPE_REGEX = "[\\s]{1}[PN]";
    public static final String DATE_TYPE_REGEX = "((((3[01]|[12][0-9]|0?[1-9])\\"
            + ".(1[012]|0?[1-9])\\.((?:19|20)\\d{2}))|(((3[01]|[12][0-9]|0?[1-9])\\"
            + ".(1[012]|0?[1-9])\\.((?:19|20)\\d{2}))-{1}((3[01]|[12][0-9]|0?[1-9])\\"
            + ".(1[012]|0?[1-9])\\.((?:19|20)\\d{2})))))";
    public static final String WAITING_TIME_REGEX = "(?:$|[\\s]{1}[\\d]{1,4})";
    public static final String TOTAL_REGEX = "^"
            + LINE_TYPE_REGEX
            + SERVICES_TYPE_REGEX
            + QUESTION_TYPE_REGEX
            + RESPONSE_TIME_TYPE_REGEX
            + DATE_TYPE_REGEX
            + WAITING_TIME_REGEX
            + "$";
}
