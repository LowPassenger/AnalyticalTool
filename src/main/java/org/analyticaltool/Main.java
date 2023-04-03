package org.analyticaltool;

import java.io.File;
import java.util.Scanner;
import org.analyticaltool.file.ReportReader;
import org.analyticaltool.service.ReportCreator;
import org.analyticaltool.utils.Storage;
import org.analyticaltool.utils.constants.AppConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final String conclusion = "See details on the app.log file.";
    private static final Scanner scanner = new Scanner(System.in);
    private static final Logger log = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        System.out.println(System.lineSeparator() + "Please enter the inner report file"
                + " for analyze or '" + AppConstants.INPUT_PATH_STOP_WORD + "' for exit app: ");
        String innerFilePath = scanner.nextLine();
        while (!new File(innerFilePath).exists()) {
            if (innerFilePath.equals(AppConstants.INPUT_PATH_STOP_WORD)) {
                log.info("The application process does not begin.");
                scanner.close();
                appComplete(666);
                return;
            }
            System.out.println("File doesn't exist! Please try again!");
            innerFilePath = scanner.nextLine();
        }
        scanner.close();
        log.info("Start work with {} file", innerFilePath);
        ReportReader reportReader = new ReportReader();
        Storage storage = new Storage();
        int appStatus = reportReader.readInnerReportFile(innerFilePath, storage);
        if (appStatus == AppConstants.WRONG_INPUT_OUTPUT_DATA_EXIT_STATUS) {
            appComplete(appStatus);
            return;
        }
        appStatus = new ReportCreator().reportCreation(storage);
        appComplete(appStatus);
    }

    public static void appComplete(int concludeCode) {
        switch (concludeCode) {
            case AppConstants.WRONG_INPUT_OUTPUT_DATA_EXIT_STATUS -> {
                log.info("Process of report creation aborted."
                        + " Cause: input/output error.");
                System.out.println("Report file wasn't created! " + conclusion);
            }
            case AppConstants.NORMAL_EXIT_STATUS -> {
                log.info("Report file was created successfully.");
                System.out.println("Report complete " + conclusion);
            }
            case AppConstants.ERROR_EXIT_STATUS -> {
                log.info("Report file was created, see errors list above.");
                System.out.println("Report complete, some errors occurred. " + conclusion);
            }
            default -> {
                log.info("Application running aborted by user.");
                System.out.println("Work complete. " + conclusion);
            }
        }
    }
}
