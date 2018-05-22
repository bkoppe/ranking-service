package de.augsburg1871.handball.ranking.persistence.mapper;

import de.augsburg1871.handball.ranking.parser.model.RankTO;
import de.augsburg1871.handball.ranking.persistence.model.Rank;
import de.augsburg1871.handball.ranking.persistence.model.Ranking;

import java.util.Set;

public final class RankMapper {

    public static final Ranking map(final Ranking ranking, Set<RankTO> rankTOs) {
        for (RankTO item : rankTOs) {
            Rank rank = new Rank.Builder(ranking, item.getMannschaft())
                    .position(item.getRang())
                    .gamesPlayed(item.getBegegnungen())
                    .wins(item.getSiege())
                    .draws(item.getUnentschieden())
                    .losses(item.getNiederlagen())
                    .goalDifferential(item.getTorDifferenz())
                    .goals(item.getTore())
                    .points(item.getPunkte())
                    .build();
        }

        return ranking;
    }

}
