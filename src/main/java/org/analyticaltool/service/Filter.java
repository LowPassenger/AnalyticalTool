package org.analyticaltool.service;

import org.analyticaltool.utils.constants.AppParseConstants;
import org.analyticaltool.utils.constants.AppQuestionBlockConstants;
import org.analyticaltool.utils.constants.AppServiceBlockConstants;

public class Filter {
    public boolean serviceFiltration(String blockToFilter, String blockForFilter) {
        if (blockToFilter.equals(blockForFilter)
                || blockToFilter.equals(AppParseConstants.SPECIAL_CHARACTER)) { return true; }
        if (!blockToFilter.contains(AppParseConstants.CATEGORIES_DELIMITER)
                & blockForFilter.contains(AppParseConstants.CATEGORIES_DELIMITER)) {
            String[] split = blockForFilter.split(AppParseConstants.CATEGORIES_DELIMITER);
            return split[AppServiceBlockConstants.SERVICE_ID_IN_BLOCK_POSITION].equals(blockToFilter);
        }
        String[] splitToFilter = blockToFilter.split(AppParseConstants.CATEGORIES_DELIMITER);
        String[] splitForFilter = blockForFilter.split(AppParseConstants.CATEGORIES_DELIMITER);
        if (splitToFilter[AppServiceBlockConstants.SERVICE_ID_IN_BLOCK_POSITION]
                .equals(AppParseConstants.SPECIAL_CHARACTER)
                & splitToFilter[AppServiceBlockConstants.SERVICE_VARIATION_ID_IN_BLOCK_POSITION]
                .equals(AppParseConstants.SPECIAL_CHARACTER)) { return true; }
        if (splitToFilter[AppServiceBlockConstants.SERVICE_ID_IN_BLOCK_POSITION]
                .equals(AppParseConstants.SPECIAL_CHARACTER)
                & splitToFilter[AppServiceBlockConstants
                .SERVICE_VARIATION_ID_IN_BLOCK_POSITION]
                .equals(splitForFilter[AppServiceBlockConstants
                .SERVICE_VARIATION_ID_IN_BLOCK_POSITION])) { return true; }
        if (splitToFilter[AppServiceBlockConstants.SERVICE_VARIATION_ID_IN_BLOCK_POSITION]
                .equals(AppParseConstants.SPECIAL_CHARACTER)
                & splitToFilter[AppServiceBlockConstants
                .SERVICE_ID_IN_BLOCK_POSITION]
                .equals(splitForFilter[AppServiceBlockConstants
                        .SERVICE_ID_IN_BLOCK_POSITION])) { return true; }
        return false;
    }

    public boolean questionFiltration(String blockToFilter, String blockForFilter) {
        if (blockToFilter.equals(blockForFilter)
                || blockToFilter.equals(AppParseConstants.SPECIAL_CHARACTER)) { return true; }
        if (!blockToFilter.contains(AppParseConstants.CATEGORIES_DELIMITER)
                & blockForFilter.contains(AppParseConstants.CATEGORIES_DELIMITER)) {
            String[] split = blockForFilter.split(AppParseConstants.CATEGORIES_DELIMITER);
            return split[AppQuestionBlockConstants.QUESTION_TYPE_ID_IN_BLOCK_POSITION]
                    .equals(blockToFilter);
        }
        String[] splitToFilter = blockToFilter.split(AppParseConstants.CATEGORIES_DELIMITER);
        String[] splitForFilter = blockForFilter.split(AppParseConstants.CATEGORIES_DELIMITER);
        if (splitToFilter[AppServiceBlockConstants.SERVICE_ID_IN_BLOCK_POSITION]
                .equals(AppParseConstants.SPECIAL_CHARACTER)
                & splitToFilter[AppServiceBlockConstants.SERVICE_VARIATION_ID_IN_BLOCK_POSITION]
                .equals(AppParseConstants.SPECIAL_CHARACTER)) { return true; }
        if (splitToFilter[AppServiceBlockConstants.SERVICE_ID_IN_BLOCK_POSITION]
                .equals(AppParseConstants.SPECIAL_CHARACTER)
                & splitToFilter[AppServiceBlockConstants
                .SERVICE_VARIATION_ID_IN_BLOCK_POSITION]
                .equals(splitForFilter[AppServiceBlockConstants
                        .SERVICE_VARIATION_ID_IN_BLOCK_POSITION])) { return true; }
        if (splitToFilter[AppServiceBlockConstants.SERVICE_VARIATION_ID_IN_BLOCK_POSITION]
                .equals(AppParseConstants.SPECIAL_CHARACTER)
                & splitToFilter[AppServiceBlockConstants
                .SERVICE_ID_IN_BLOCK_POSITION]
                .equals(splitForFilter[AppServiceBlockConstants
                        .SERVICE_ID_IN_BLOCK_POSITION])) { return true; }
        return false;
    }
}
