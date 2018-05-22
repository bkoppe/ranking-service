package de.augsburg1871.handball.ranking.persistence.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.HashMap;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public enum Season {

    SEASON_2017_2018("2017-18"),
    SEASON_2018_2019("2018-19");

    private static final Map<String, Season> LOOKUP = new HashMap<String, Season>();

    static {
        for (Season season : Season.values()) {
            LOOKUP.put(season.getId(), season);
        }
    }

    private final String id;

    Season(final String id) {
        this.id = id;
    }

    public static final Season getById(String id) {
        checkArgument(isNotBlank(id), "ID must not be blank!");
        return LOOKUP.get(id);
    }

    public String getId() {
        return id;
    }

    @Converter(autoApply = true)
    public static final class SeasonConverter implements AttributeConverter<Season, String> {

        @Override
        public String convertToDatabaseColumn(final Season attribute) {
            return attribute.getId();
        }

        @Override
        public Season convertToEntityAttribute(final String dbData) {
            if (isBlank(dbData)) {
                return null;
            }

            return Season.getById(dbData);
        }

    }

}
