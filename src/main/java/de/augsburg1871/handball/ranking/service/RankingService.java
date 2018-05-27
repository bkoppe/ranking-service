package de.augsburg1871.handball.ranking.service;

import de.augsburg1871.handball.ranking.persistence.model.AgeGroup;
import de.augsburg1871.handball.ranking.persistence.model.Ranking;
import de.augsburg1871.handball.ranking.persistence.model.Season;

import java.util.List;

public interface RankingService {

    /**
     * <p>Current ranking for the given ageGroup.</p>
     *
     * @param ageGroup the ageGroup of interest
     * @return Current Ranking for the ageGroup
     */
    Ranking getCurrentRankingFor(AgeGroup ageGroup);

    /**
     * <p>Ranking for the given ageGroup and season. Use this method for past seasons.
     * Use {@link #getCurrentRankingFor(AgeGroup)} to get the ranking of the current season.</p>
     *
     * @param season   the season of interest
     * @param ageGroup the ageGroup of interest
     * @return Ranking for the ageGroup in a special season.
     */
    Ranking getRankingFor(Season season, AgeGroup ageGroup);

    /**
     * <p>All rankings for the given season.</p>
     * @param season the season of interest
     * @return All rankings for the given season.
     */
    List<Ranking> getRankings(Season season);

}
