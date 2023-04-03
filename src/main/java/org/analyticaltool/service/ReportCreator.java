package org.analyticaltool.service;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.analyticaltool.file.OutputFileWriter;
import org.analyticaltool.utils.Storage;
import org.analyticaltool.utils.constants.AppConstants;
import org.analyticaltool.utils.constants.AppParseConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReportCreator {
    private static final int METHOD_STATUS_ERROR_CODE = 0;
    private static final Logger log = LogManager.getLogger(ReportCreator.class);
    private final OutputFileWriter writer = new OutputFileWriter();
    private final Filter filter = new Filter();
    private int reportCreationErrorStatus = AppConstants.NORMAL_EXIT_STATUS;
    private List<String[]> waitingTimeLines;

    public int reportCreation(Storage storage) {
        log.info("Report creation process started.");
        File outputFile = writer.createOutputFile();
        waitingTimeLines = storage.getWaitingTimeLines();
        Set<Map.Entry<String[], Integer>> entries = storage.getQueries().entrySet();
        for (Map.Entry<String[], Integer> entry : entries) {
            int averageTime = averageTimeCalculate(entry.getKey(), entry.getValue());
            if (averageTime == METHOD_STATUS_ERROR_CODE) {
                reportCreationErrorStatus = writer.writeOutputFile(outputFile,
                        AppConstants.REPORT_FILE_DOESNT_MATCH_SYMBOL);
            } else {
                reportCreationErrorStatus = writer.writeOutputFile(outputFile,
                        String.valueOf(averageTime));
            }
            if (reportCreationErrorStatus == AppConstants.WRONG_INPUT_OUTPUT_DATA_EXIT_STATUS) {
                return AppConstants.WRONG_INPUT_OUTPUT_DATA_EXIT_STATUS;
            }
        }
        return reportCreationErrorStatus;
    }

    private int averageTimeCalculate(String[] query, Integer searchLimit) {
        if (query == null || searchLimit == null || waitingTimeLines == null
                || searchLimit > waitingTimeLines.size()) {
            log.warn("An error occurred during data processing!");
            reportCreationErrorStatus = AppConstants.ERROR_EXIT_STATUS;
            return METHOD_STATUS_ERROR_CODE;
        }
        int result = 0;
        int counter = 0;
        for (int i = 0; i < searchLimit; i++) {
            if (dataFiltration(query, waitingTimeLines.get(i))) {
                result += Integer.parseInt(waitingTimeLines
                        .get(i)[AppParseConstants.WAITING_TIME_BLOCK]);
                counter++;
            }
        }
        return counter == 0 ? 0 : result / counter;
    }

    private boolean dataFiltration(String[] query, String[] waitingTimeLine) {
        if (query == null || waitingTimeLine == null) {
            return false;
        }
        return (filter.serviceFiltration(query[AppParseConstants
                .SERVICE_AND_SERVICE_VARIATION_ID_BLOCK], waitingTimeLine[AppParseConstants
                .SERVICE_AND_SERVICE_VARIATION_ID_BLOCK])
                & filter.questionFiltration(query[AppParseConstants
                .QUESTION_TYPE_AND_ITS_SUBCATEGORIES_ID_BLOCK], waitingTimeLine[AppParseConstants
                .QUESTION_TYPE_AND_ITS_SUBCATEGORIES_ID_BLOCK])
                & filter.responseTypeFiltration(query[AppParseConstants.RESPONSE_TYPE_BLOCK],
                waitingTimeLine[AppParseConstants.RESPONSE_TYPE_BLOCK])
                & filter.dateFiltration(query[AppParseConstants.DATE_BLOCK],
                waitingTimeLine[AppParseConstants.DATE_BLOCK]));
    }

}
