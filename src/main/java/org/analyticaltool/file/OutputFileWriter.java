package org.analyticaltool.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import org.analyticaltool.utils.constants.AppConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OutputFileWriter {
    private static final Logger log = LogManager.getLogger(OutputFileWriter.class);

    public int writeOutputFile(File toFile, String line) {
        int output = AppConstants.NORMAL_EXIT_STATUS;
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile, true))) {
            bufferedWriter.write(line + System.lineSeparator());
        } catch (IOException e) {
            log.error("Error while writing report to file {}, stacktrace: " + e,
                    toFile.getAbsolutePath());

            return AppConstants.WRONG_INPUT_OUTPUT_DATA_EXIT_STATUS;
        }
        return output;
    }

    public File createOutputFile() {
        File reportDirectory = new File(AppConstants.OUTPUT_REPORT_DIRECTORY);
        if (!reportDirectory.exists()) {
            reportDirectory.mkdir();
        }
        String reportFileTotalPath = (AppConstants.OUTPUT_REPORT_DIRECTORY)
                .concat(AppConstants.OUTPUT_FILE_TEMPLATE_NAME)
                .concat(LocalDateTime.now().format(AppConstants.FORMATTER_FOR_OUTPUT_FILE))
                .concat(AppConstants.OUTPUT_FILE_EXTENTION);
        return new File(reportFileTotalPath);
    }
}
