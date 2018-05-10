package de.augsburg1871.handball.rankingservice.parser;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.select.Elements;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class ValueExtractor {

    private static Log LOG = LogFactory.getLog(ValueExtractor.class);

    public static int getAsInt(final Elements elements, final int index)
    {
        final String value = elements.get(index).text();
        if (isBlank(value)) {
            return 0;
        }

        int intValue = 0;
        try {
            intValue = Integer.parseInt(value.replace("\u00a0", "").trim());
        } catch (final NumberFormatException e) {
            LOG.error(e);
        }

        return intValue;
    }

    public static String getAsString(final Elements elements, final int index)
    {
        final String value = elements.get(index).text();
        if (isBlank(value)) {
            return null;
        }

        return value.replace("\u00a0", "").trim();
    }

}
