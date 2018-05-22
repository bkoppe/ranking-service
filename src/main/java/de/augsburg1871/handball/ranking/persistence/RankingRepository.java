package de.augsburg1871.handball.ranking.persistence;

import de.augsburg1871.handball.ranking.persistence.model.Ranking;
import de.augsburg1871.handball.ranking.persistence.model.embeddable.RankingId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RankingRepository extends JpaRepository<Ranking, RankingId> {

}
