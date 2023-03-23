package org.analyticaltool.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.analyticaltool.Main;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReportReader {
    private static final int EXIT_STATUS = 1;
    private static final String ERROR_MESSAGE =
            "The report file doesn't contain correct data for parsing!";
    private static final Logger log = LogManager.getLogger(Main.class);
    private final List<String> inputLines = new ArrayList<>();

    public List<String> readReport(String reportFileName) {
        File report = new File(reportFileName);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(report))) {
            String lineFromFile = bufferedReader.readLine();
            if (!fileIsValid(lineFromFile)) {
                List<String> errorReport = new ArrayList<>();
                errorReport.add(ERROR_MESSAGE);
                new OutputFileWriter().writeOutputFile(errorReport);
                System.exit(EXIT_STATUS);
             }
            lineFromFile = bufferedReader.readLine();
            while (lineFromFile != null) {
                if (ipValidator.isValid(lineFromFile)) {
                    validIpList.add(lineFromFile);
                } else {
                    log.warn("The {} IP address in {} file is not correct!"
                                    + " Please renew the access control list!",
                            lineFromFile, blackListFileName);
                }
                lineFromFile = bufferedReader.readLine();
            }
        } catch (IOException e) {
            log.error("The read from file {} error is occurred!", blackListFileName);
            throw new RuntimeException("Read error from file " + blackListFileName + "!", e);
        }
        return validIpList;
    }

    private boolean lineIsValid(String line) {
        return false;
    }

    private boolean fileIsValid(String firstLine) {
        try {
            int linesNumber= Integer.parseInt(firstLine);
            if (linesNumber <= 0 || linesNumber >= Main.REPORT_FILE_MAX_RECORDS_NUMBER) {
                log.warn("The report file records number is wrong!");
                return false;
            }
        } catch (NumberFormatException e) {
            log.error(ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}
