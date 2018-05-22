package de.augsburg1871.handball.ranking.persistence.mapper;

import de.augsburg1871.handball.ranking.parser.model.RankingTO;
import de.augsburg1871.handball.ranking.persistence.mapper.NoteMapper;
import de.augsburg1871.handball.ranking.persistence.mapper.RankMapper;
import de.augsburg1871.handball.ranking.persistence.model.Ranking;

public final class RankingMapper {

    public static final Ranking map(final Ranking ranking, final RankingTO rankingTO) {
        RankMapper.map(ranking, rankingTO.getRankTOs());
        NoteMapper.map(ranking, rankingTO.getNoteTOs());

        return ranking;
    }

}
