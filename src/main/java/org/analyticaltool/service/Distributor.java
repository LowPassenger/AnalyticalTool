package org.analyticaltool.service;

import java.util.HashMap;
import java.util.Map;
import org.analyticaltool.utils.Storage;
import org.analyticaltool.utils.constants.AppConstants;
import org.analyticaltool.utils.constants.AppErrorConstants;
import org.analyticaltool.utils.constants.AppParseConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Distributor {
    private static final Logger log = LogManager.getLogger(Distributor.class);
    private final Validator validator = new Validator();
    private final Storage storage = new Storage();
    public int lineRecognize(String line, int lineCounter) {
        if (!validator.regexValidation(line)) {
            log.warn(AppErrorConstants.REGEX_VALIDATION_ERROR + lineCounter);
            return AppConstants.ERROR_EXIT_STATUS;
        }

        String[] dataForParsing = line.split(AppParseConstants.DATA_IN_LINE_DELIMITER);
        if (dataForParsing.length < AppParseConstants.DATA_BLOCKS_IN_QUERY_QUANTITY
                | dataForParsing.length > AppParseConstants.DATA_BLOCKS_IN_WAITING_TIMELINE_QUANTITY) {
            log.warn(AppErrorConstants.DATA_PARSING_ERROR + lineCounter);
            return AppConstants.ERROR_EXIT_STATUS;
        }

        if (!validator.dataExtractionValidation(dataForParsing[AppParseConstants.DATA_IN_LINE_TYPE],
                dataForParsing.length)) {
            log.warn(AppErrorConstants.LINE_TYPE_VALIDATION_ERROR + lineCounter);
            return AppConstants.ERROR_EXIT_STATUS;
        }

        if (!validator.serviceAndVariationsValidation(dataForParsing[AppParseConstants
                .SERVICE_AND_SERVICE_VARIATION_ID_BLOCK])) {
            log.warn(AppErrorConstants.SERVICE_ID_VALIDATION_ERROR + lineCounter);
            return AppConstants.ERROR_EXIT_STATUS;
        }

        if (!validator.questionAndCategoriesValidation(dataForParsing[AppParseConstants
                .QUESTION_TYPE_AND_ITS_SUBCATEGORIES_ID_BLOCK])) {
            log.warn(AppErrorConstants.QUESTION_TYPE_VALIDATION_ERROR + lineCounter);
            return AppConstants.ERROR_EXIT_STATUS;
        }

        if (!validator.responseTypeValidator(dataForParsing[AppParseConstants
                .RESPONSE_TYPE_BLOCK])) {
            log.warn(AppErrorConstants.RESPONSE_TYPE_VALIDATION_ERROR + lineCounter);
            return AppConstants.ERROR_EXIT_STATUS;
        }

        if (!validator.dateValidation(dataForParsing[AppParseConstants.DATE_BLOCK])) {
            log.warn(AppErrorConstants.DATE_VALIDATION_ERROR + lineCounter);
            return AppConstants.ERROR_EXIT_STATUS;
        }

        if ((dataForParsing.length == AppParseConstants
                .DATA_BLOCKS_IN_WAITING_TIMELINE_QUANTITY)
                & !validator.waitingTimeValidation(dataForParsing[AppParseConstants
                .WAITING_TIME_BLOCK])) {
            log.warn(AppErrorConstants.WAITING_TIME_VALIDATION_ERROR + lineCounter);
            return AppConstants.ERROR_EXIT_STATUS;
        }

        if (dataForParsing[AppParseConstants.DATA_IN_LINE_TYPE]
                .equals(AppParseConstants.DATA_TYPE_WAITING_TIMELINE)) {
            storage.getWaitingTimeLines().add(dataForParsing);
        } else {
            Map<String[], Integer> query = new HashMap<>();
            query.put(dataForParsing, dataForParsing.length);
            storage.getQueries().add(query);
        }
        return AppConstants.NORMAL_EXIT_STATUS;
    }
}
