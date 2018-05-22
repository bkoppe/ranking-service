package de.augsburg1871.handball.ranking.persistence.model.comparator;

import de.augsburg1871.handball.ranking.persistence.model.Rank;

import java.util.Comparator;

public class RankComparator implements Comparator<Rank> {

    private final Comparator<Rank> comparator;

    public RankComparator() {
        this.comparator = Comparator.comparing(Rank::getPosition);
    }

    @Override
    public int compare(final Rank o1, final Rank o2) {
        return comparator.compare(o1, o2);
    }

}
