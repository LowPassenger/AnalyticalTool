package org.analyticaltool.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.analyticaltool.utils.AppConstants;
import org.analyticaltool.utils.Regex;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Validator {
    private static final Logger log = LogManager.getLogger(Validator.class);

    public boolean fileIsValid(String firstLine) {
        try {
            int linesNumber= Integer.parseInt(firstLine);
            if (linesNumber <= 0 || linesNumber >= AppConstants.REPORT_FILE_MAX_RECORDS_NUMBER) {
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
        if (operationType == null) { return false; }
        return ((operationType.equals(AppConstants.DATA_TYPE_WAITING_TIMELINE)
                & lineBlocksQuantity == AppConstants.DATA_BLOCKS_IN_WAITING_TIMELINE_QUANTITY)
                | (operationType.equals(AppConstants.DATA_TYPE_QUERY)
                & lineBlocksQuantity == AppConstants.DATA_BLOCKS_IN_QUERY_QUANTITY));
    }

    public boolean serviceAndVariationsValidation(String serviceBlock) {
        if (serviceBlock == null || serviceBlock.isEmpty()) { return false; }
        if (isNotNumberOrAsterisk(serviceBlock)
                || Integer.parseInt(serviceBlock) < AppConstants.SERVICE_ID_START_NUMBER
                || Integer.parseInt(serviceBlock) > AppConstants.SERVICE_ID_END_NUMBER) { return  false; }
        if (serviceBlock.contains(AppConstants.CATEGORIES_DELIMITER)) {
            String[] serviceVariations = serviceBlock.split(AppConstants.CATEGORIES_DELIMITER);
            for (String value : serviceVariations) {
                if (value.isEmpty() || isNotNumberOrAsterisk(value)) { return false; }
            }
        }
        return true;
    }
    public boolean questionValidation(String questionString) {

    }

    private boolean isNotNumberOrAsterisk(String value) {
        if (value.equals(AppConstants.SPECIAL_CHARACTER)) { return false; }
        try {
            Integer.parseInt(value);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }
}
