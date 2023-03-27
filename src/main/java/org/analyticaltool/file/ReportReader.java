package org.analyticaltool.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.analyticaltool.Main;
import org.analyticaltool.service.Distributor;
import org.analyticaltool.service.Validator;
import org.analyticaltool.utils.AppConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReportReader {
    private static final Logger log = LogManager.getLogger(ReportReader.class);
    private final Validator validator = new Validator();
    private final Distributor distributor = new Distributor();

    public int readInnerReportFile(String innerFileName) {
        int output = AppConstants.NORMAL_EXIT_STATUS;
        File innerFile = new File(innerFileName);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(innerFile))) {
            String lineFromFile = bufferedReader.readLine();
            if (!validator.fileIsValid(lineFromFile)) {
                bufferedReader.close();
                Main.appComplete(AppConstants.WRONG_INPUT_DATA_EXIT_STATUS);
             }
            lineFromFile = bufferedReader.readLine();
            int lineNumber = 2;
            while (lineFromFile != null) {
                lineFromFile = bufferedReader.readLine();
                lineNumber++;
                output = distributor.lineRecognize(lineFromFile, lineNumber);
            }
        } catch (IOException e) {
            log.error("The read from file {} error is occurred!", innerFileName);
            Main.appComplete(AppConstants.WRONG_INPUT_DATA_EXIT_STATUS);
        }
        return output;
    }
}
