package de.augsburg1871.handball.ranking.persistence.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.HashMap;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;
import static de.augsburg1871.handball.ranking.persistence.model.Sex.*;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public enum AgeGroup {

    MINIS("minis", UNISEX),
    GEMISCHTE_E_JUGEND("gem-e", UNISEX),
    MAENNLICHE_D_JUGEND("m-d", MALE),
    MAENNLICHE_C_JUGEND("m-c", MALE),
    MAENNLICHE_B_JUGEND("m-b", MALE),
    MAENNLICHE_A_JUGEND("m-a", MALE),
    FRAUEN("f-1", FEMALE),
    MAENNER_1("m-1", MALE),
    MAENNER_2("m-2", MALE);

    private static final Map<String, AgeGroup> LOOKUP = new HashMap<String, AgeGroup>();

    static {
        for (AgeGroup ageGroup : AgeGroup.values()) {
            LOOKUP.put(ageGroup.getShortcode(), ageGroup);
        }
    }

    private final String shortcode;
    private final Sex sex;

    AgeGroup(final String shortcode, final Sex sex) {
        this.shortcode = shortcode;
        this.sex = sex;
    }

    public static AgeGroup getByShortcode(String shortcode) {
        checkArgument(isNotBlank(shortcode), "Shortcode must not be blank!");
        return LOOKUP.get(shortcode);
    }

    public String getShortcode() {
        return shortcode;
    }

    public Sex getSex() {
        return sex;
    }

    @Converter(autoApply = true)
    public static final class AgeGroupConverter implements AttributeConverter<AgeGroup, String> {

        @Override
        public String convertToDatabaseColumn(final AgeGroup attribute) {
            return attribute.getShortcode();
        }

        @Override
        public AgeGroup convertToEntityAttribute(final String dbData) {
            if (isBlank(dbData)) {
                return null;
            }

            return AgeGroup.getByShortcode(dbData);
        }
    }

}
