package org.analyticaltool.service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;
import org.analyticaltool.utils.constants.AppDateConstants;
import org.analyticaltool.utils.constants.AppParseConstants;
import org.analyticaltool.utils.constants.AppQuestionBlockConstants;
import org.analyticaltool.utils.constants.AppServiceBlockConstants;

public class Filter {
    public boolean serviceFiltration(String blockToFilter, String blockForFilter) {
        if (blockToFilter.equals(blockForFilter)
                || blockToFilter.equals(AppParseConstants.SPECIAL_CHARACTER)) {
            return true;
        }
        if (!blockToFilter.contains(AppParseConstants.CATEGORIES_DELIMITER)
                & blockForFilter.contains(AppParseConstants.CATEGORIES_DELIMITER)) {
            String[] split = blockForFilter.split(Pattern.quote(AppParseConstants
                    .CATEGORIES_DELIMITER));
            return split[AppServiceBlockConstants.SERVICE_ID_IN_BLOCK_POSITION]
                    .equals(blockToFilter);
        }
        return false;
    }

    public boolean questionFiltration(String blockToFilter, String blockForFilter) {
        if (blockToFilter.equals(blockForFilter)
                || blockToFilter.equals(AppParseConstants.SPECIAL_CHARACTER)) {
            return true;
        }
        if (!blockToFilter.contains(AppParseConstants.CATEGORIES_DELIMITER)
                & blockForFilter.contains(AppParseConstants.CATEGORIES_DELIMITER)) {
            String[] split = blockForFilter.split(Pattern.quote(AppParseConstants
                    .CATEGORIES_DELIMITER));
            return split[AppQuestionBlockConstants.QUESTION_TYPE_ID_IN_BLOCK_POSITION]
                    .equals(blockToFilter);
        }
        String[] splitToFilter = blockToFilter.split(Pattern.quote(AppParseConstants
                .CATEGORIES_DELIMITER));
        String[] splitForFilter = blockForFilter.split(Pattern.quote(AppParseConstants
                .CATEGORIES_DELIMITER));
        if (splitToFilter.length == AppQuestionBlockConstants
                .QUESTION_SUBCATEGORY_ID_IN_BLOCK_POSITION
                & splitForFilter.length == AppQuestionBlockConstants
                .QUESTION_FULL_INFORMATION_SLOTS_QUANTITY) {
            if (splitToFilter[AppServiceBlockConstants.SERVICE_ID_IN_BLOCK_POSITION]
                    .equals(splitForFilter[AppServiceBlockConstants.SERVICE_ID_IN_BLOCK_POSITION])
                     & splitToFilter[AppServiceBlockConstants
                    .SERVICE_VARIATION_ID_IN_BLOCK_POSITION]
                    .equals(splitForFilter[AppServiceBlockConstants
                            .SERVICE_VARIATION_ID_IN_BLOCK_POSITION])) {
                return true;
            }
        }
        return false;
    }

    public boolean responseTypeFiltration(String blockToFilter, String blockForFilter) {
        return blockToFilter.equals(blockForFilter);
    }

    public boolean dateFiltration(String blockToFilter, String blockForFilter) {
        if (blockToFilter.equals(blockForFilter)) {
            return true;
        }

        LocalDate timeLineDate = dateParser(blockForFilter);
        if (timeLineDate.isEqual(LocalDate.MIN)) {
            return false;
        }

        if (!blockToFilter.contains(AppParseConstants.DATE_DELIMITER)) {
            return dateParser(blockToFilter).isEqual(timeLineDate);
        }

        String[] queryPeriod = blockToFilter.split(AppParseConstants.DATE_DELIMITER);
        if (queryPeriod.length != AppDateConstants
                .DATE_FULL_INFORMATION_SLOTS_QUANTITY) {
            return false;
        }

        LocalDate queryStartDate = dateParser(queryPeriod[AppDateConstants
                .QUERY_START_DATE_SLOT_NUMBER]);
        LocalDate queryEndDate = dateParser(queryPeriod[AppDateConstants
                .QUERY_END_DATE_SLOT_NUMBER]);
        if (queryStartDate.isEqual(LocalDate.MIN)
                || queryEndDate.isEqual(LocalDate.MIN)) {
            return false;
        }

        if (timeLineDate.isAfter(queryStartDate)
                & timeLineDate.isBefore(queryEndDate)) {
            return true;
        }
        return false;
    }

    private LocalDate dateParser(String date) {
        try {
            return LocalDate.parse(date, AppDateConstants.DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            return LocalDate.MIN;
        }
    }
}
