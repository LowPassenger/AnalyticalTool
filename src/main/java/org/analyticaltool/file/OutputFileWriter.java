package org.analyticaltool.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.analyticaltool.Main;
import org.analyticaltool.utils.constants.AppConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OutputFileWriter {
    private static final Logger log = LogManager.getLogger(OutputFileWriter.class);
    private final File toFile = new File(AppConstants.OUTPUT_REPORT_FILE);

    public void writeOutputFile(String line) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile, true))) {
            bufferedWriter.write(line);
        } catch (IOException e) {
            log.error("Error while writing report to file {}, stacktrace: " + e,
                    AppConstants.OUTPUT_REPORT_FILE);
            Main.appComplete(AppConstants.WRONG_INPUT_DATA_EXIT_STATUS);
        }
    }
}
