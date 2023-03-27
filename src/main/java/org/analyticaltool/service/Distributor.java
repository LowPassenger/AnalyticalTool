package org.analyticaltool.service;

import org.analyticaltool.utils.AppConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Distributor {
    private static final Logger log = LogManager.getLogger(Distributor.class);
    private final Validator validator = new Validator();
    public int lineRecognize(String line, int lineCounter) {
        int output = AppConstants.NORMAL_EXIT_STATUS;
        if (!validator.regexValidation(line)) {
            log.warn(AppConstants.REGEX_VALIDATION_ERROR + lineCounter);
            output = AppConstants.ERROR_EXIT_STATUS;
        }
        String[] dataForParsing = line.split(AppConstants.DATA_IN_LINE_DELIMITER);
        if (!validator.dataExtractionValidation(dataForParsing[AppConstants.DATA_IN_LINE_TYPE],
                dataForParsing.length)) {
            log.warn(AppConstants.LINE_TYPE_VALIDATION_ERROR + lineCounter);
            output = AppConstants.ERROR_EXIT_STATUS;
        }

        return output;
    }
}
