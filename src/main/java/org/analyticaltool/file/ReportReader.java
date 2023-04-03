package org.analyticaltool.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.analyticaltool.service.Distributor;
import org.analyticaltool.service.Validator;
import org.analyticaltool.utils.Storage;
import org.analyticaltool.utils.constants.AppConstants;
import org.analyticaltool.utils.constants.AppParseConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReportReader {
    private static final Logger log = LogManager.getLogger(ReportReader.class);
    private final Validator validator = new Validator();
    private final Distributor distributor = new Distributor();

    public int readInnerReportFile(String innerFileName, Storage storage) {
        int output = AppConstants.NORMAL_EXIT_STATUS;
        File innerFile = new File(innerFileName);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(innerFile))) {
            String lineFromFile = bufferedReader.readLine();
            if (!validator.fileIsValid(lineFromFile)) {
                bufferedReader.close();
                return AppConstants.WRONG_INPUT_OUTPUT_DATA_EXIT_STATUS;
            }
            lineFromFile = bufferedReader.readLine();
            int lineNumber = AppParseConstants.START_POSITION_FOR_PARSE_LINES_IN_FILE;
            int wrongLinesCounter = 0;
            while (lineFromFile != null) {
                output = distributor.lineRecognize(lineFromFile, lineNumber, storage);
                if (output == AppConstants.ERROR_EXIT_STATUS) {
                    wrongLinesCounter++;
                }
                lineFromFile = bufferedReader.readLine();
                lineNumber++;
            }
            log.info("The file {} reading complete. Total lines processed number: {}."
                    + " Total number of lines with read errors: {}.", innerFileName,
                    lineNumber, wrongLinesCounter);
        } catch (IOException e) {
            log.error("The read from file {} error is occurred! Stacktrace: " + e, innerFileName);
            return AppConstants.WRONG_INPUT_OUTPUT_DATA_EXIT_STATUS;
        }
        return output;
    }
}
