package de.augsburg1871.handball.ranking;

import de.augsburg1871.handball.ranking.persistence.model.Season;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "ranking")
public class RankingProperties {

    Season currentSeason;

    public Season getCurrentSeason() {
        return currentSeason;
    }

    public void setCurrentSeason(final Season currentSeason) {
        this.currentSeason = currentSeason;
    }

}
