package org.analyticaltool.service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.analyticaltool.utils.constants.AppConstants;
import org.analyticaltool.utils.Regex;
import org.analyticaltool.utils.constants.AppDateConstants;
import org.analyticaltool.utils.constants.AppParseConstants;
import org.analyticaltool.utils.constants.AppQuestionBlockConstants;
import org.analyticaltool.utils.constants.AppServiceBlockConstants;
import org.analyticaltool.utils.constants.AppWaitingTimeConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Validator {
    private static final Logger log = LogManager.getLogger(Validator.class);

    public boolean fileIsValid(String firstLine) {
        try {
            int linesNumber= Integer.parseInt(firstLine);
            if (linesNumber <= 0 || linesNumber > AppConstants.REPORT_FILE_MAX_RECORDS_NUMBER) {
                log.warn("The report file records number is wrong!");
                return false;
            }
        } catch (NumberFormatException e) {
            log.error("The report file doesn't contain correct data for parsing!");
            return false;
        }
        return true;
    }

    public boolean regexValidation(String stringFromFile) {
        Pattern pattern = Pattern.compile(Regex.TOTAL_REGEX);
        Matcher matcher = pattern.matcher(stringFromFile);
        return matcher.matches();
    }

    public boolean dataExtractionValidation(String operationType, int lineBlocksQuantity) {
        if (operationType == null || operationType.isEmpty()) { return false; }
        return ((operationType.equals(AppParseConstants.DATA_TYPE_WAITING_TIMELINE)
                & lineBlocksQuantity == AppParseConstants.DATA_BLOCKS_IN_WAITING_TIMELINE_QUANTITY)
                || (operationType.equals(AppParseConstants.DATA_TYPE_QUERY)
                & lineBlocksQuantity == AppParseConstants.DATA_BLOCKS_IN_QUERY_QUANTITY));
    }

    public boolean serviceAndVariationsValidation(String serviceBlock) {
        if (serviceBlock == null || serviceBlock.isEmpty()) { return false; }

        if (serviceBlock.contains(AppParseConstants.CATEGORIES_DELIMITER)) {
            String[] serviceVariations = serviceBlock
                    .split(AppParseConstants.CATEGORIES_DELIMITER);
            if (serviceVariations.length > AppServiceBlockConstants
                    .SERVICE_FULL_INFORMATION_SLOTS_QUANTITY) { return false; }

            String serviceId = serviceVariations[AppServiceBlockConstants
                    .SERVICE_ID_IN_BLOCK_POSITION];
            String serviceVariationId = serviceVariations[AppServiceBlockConstants
                    .SERVICE_VARIATION_ID_IN_BLOCK_POSITION];

            if (serviceId == null || serviceId.isEmpty() || isNotNumberOrAsterisk(serviceId)
                    || Integer.parseInt(serviceId) < AppServiceBlockConstants
                    .SERVICE_ID_START_NUMBER
                    || Integer.parseInt(serviceId) > AppServiceBlockConstants
                    .SERVICE_ID_END_NUMBER)
            { return false; }

            if (serviceVariationId == null ||serviceVariationId.isEmpty()
                    || isNotNumberOrAsterisk(serviceVariationId)
                    || Integer.parseInt(serviceVariationId) < AppServiceBlockConstants
                    .SERVICE_VARIATION_ID_START_NUMBER
                    || Integer.parseInt(serviceVariationId) > AppServiceBlockConstants
                    .SERVICE_VARIATION_ID_END_NUMBER)
            { return false; }

            if (isNotNumberOrAsterisk(serviceBlock)
                    || Integer.parseInt(serviceBlock)
                    < AppServiceBlockConstants.SERVICE_ID_START_NUMBER
                    || Integer.parseInt(serviceBlock)
                    > AppServiceBlockConstants.SERVICE_ID_END_NUMBER)
            { return  false; }
        }
        return true;
    }
    public boolean questionAndCategoriesValidation(String questionString) {
        if (questionString == null || questionString.isEmpty()) { return false; }

        if (questionString.contains(AppParseConstants.CATEGORIES_DELIMITER)) {
            String[] questionVariations = questionString.split(AppParseConstants.CATEGORIES_DELIMITER);
            if (questionVariations.length > AppQuestionBlockConstants
                    .QUESTION_FULL_INFORMATION_SLOTS_QUANTITY) { return false; }

            String questionTypeId = questionVariations[AppQuestionBlockConstants
                    .QUESTION_TYPE_ID_IN_BLOCK_POSITION];
            String questionCategoryId = questionVariations[AppQuestionBlockConstants
                    .QUESTION_CATEGORY_ID_IN_BLOCK_POSITION];
            if (questionVariations.length == AppQuestionBlockConstants
                    .QUESTION_FULL_INFORMATION_SLOTS_QUANTITY) {
                String questionSubcategoryId = questionVariations[AppQuestionBlockConstants
                        .QUESTION_SUBCATEGORY_ID_IN_BLOCK_POSITION];
                if (questionSubcategoryId == null || questionSubcategoryId.isEmpty()
                        || isNotNumberOrAsterisk(questionSubcategoryId)
                        || Integer.parseInt(questionSubcategoryId) < AppQuestionBlockConstants
                        .QUESTION_SUBCATEGORY_ID_START_NUMBER
                        || Integer.parseInt(questionSubcategoryId) > AppQuestionBlockConstants
                        .QUESTION_SUBCATEGORY_ID_END_NUMBER)
                { return false; }
            }

            if (questionTypeId == null || questionTypeId.isEmpty()
                    || isNotNumberOrAsterisk(questionTypeId)
                    || Integer.parseInt(questionTypeId) < AppQuestionBlockConstants
                    .QUESTION_TYPE_ID_START_NUMBER
                    || Integer.parseInt(questionTypeId) > AppQuestionBlockConstants
                    .QUESTION_TYPE_ID_END_NUMBER)
            { return false; }

            if (questionCategoryId == null || questionCategoryId.isEmpty()
                    || isNotNumberOrAsterisk(questionCategoryId)
                    || Integer.parseInt(questionCategoryId) < AppQuestionBlockConstants
                    .QUESTION_CATEGORY_ID_START_NUMBER
                    || Integer.parseInt(questionCategoryId) > AppQuestionBlockConstants
                    .QUESTION_CATEGORY_ID_END_NUMBER)
            { return false; }

            if (isNotNumberOrAsterisk(questionString)
                    || Integer.parseInt(questionString)
                    < AppQuestionBlockConstants.QUESTION_TYPE_ID_START_NUMBER
                    || Integer.parseInt(questionString)
                    > AppQuestionBlockConstants.QUESTION_TYPE_ID_END_NUMBER)
            { return  false; }
        }
        return true;
    }

    public boolean responseTypeValidator(String responseType) {
        if (responseType == null || responseType.isEmpty()) { return  false; }
        return (responseType.equals(AppParseConstants.RESPONSE_TIME_FIRST_ANSWER)
                || responseType.equals(AppParseConstants.RESPONSE_TIME_NEXT_ANSWER));
    }

    public boolean dateValidation(String dateBlock) {
        if (dateBlock == null || dateBlock.isEmpty()) { return  false; }

        if (dateBlock.contains(AppParseConstants.DATE_DELIMITER)) {
            String[] datePeriod = dateBlock.split(AppParseConstants.DATE_DELIMITER);
            if (datePeriod.length > AppDateConstants
                    .DATE_FULL_INFORMATION_SLOTS_QUANTITY) { return  false; }
            for (String date : datePeriod) {
                if (date == null || date.isEmpty() || isNotValidDate(date)) { return  false; }
            }
        }

        if (isNotValidDate(dateBlock)) { return  false; }
        return true;
    }

    public boolean waitingTimeValidation(String waitingTime) {
        if (waitingTime == null || waitingTime.isEmpty()) { return  false; }
        return ((!waitingTime.equals(AppParseConstants.SPECIAL_CHARACTER)
                & !isNotNumberOrAsterisk(waitingTime)) &
                !(Integer.parseInt(waitingTime)
                        < AppWaitingTimeConstants.MIN_WAITING_TIME_MINUTES)
                || (Integer.parseInt(waitingTime)
                > AppWaitingTimeConstants.MAX_WAITING_TIME_MINUTES));
    }

    private boolean isNotNumberOrAsterisk(String value) {
        if (value.equals(AppParseConstants.SPECIAL_CHARACTER)) { return false; }
        try {
            Integer.parseInt(value);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    private boolean isNotValidDate(String date) {
        LocalDate dateFromString;
        try {
            dateFromString = LocalDate.parse(date, AppDateConstants.DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            return true;
        }
        return (dateFromString == null || dateFromString.isBefore(AppDateConstants.START_DATE)
                || dateFromString.isAfter(AppDateConstants.END_DATE));
    }
}
