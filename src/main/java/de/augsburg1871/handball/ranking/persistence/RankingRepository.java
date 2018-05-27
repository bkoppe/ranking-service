package de.augsburg1871.handball.ranking.persistence;

import de.augsburg1871.handball.ranking.persistence.model.Ranking;
import de.augsburg1871.handball.ranking.persistence.model.Season;
import de.augsburg1871.handball.ranking.persistence.model.embeddable.RankingId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RankingRepository extends JpaRepository<Ranking, RankingId> {

    @Query(value = "select * from ranking_meta_data where season = ?1", nativeQuery = true)
    List<Ranking> findAllBySeason(Season season);

}
