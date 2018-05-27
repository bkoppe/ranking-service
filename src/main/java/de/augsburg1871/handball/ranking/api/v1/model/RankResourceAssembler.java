package de.augsburg1871.handball.ranking.api.v1.model;

import de.augsburg1871.handball.ranking.api.v1.RankingController;
import de.augsburg1871.handball.ranking.persistence.model.Rank;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class RankResourceAssembler extends ResourceAssemblerSupport<Rank, RankResource> {

    public RankResourceAssembler() {
        super(RankingController.class, RankResource.class);
    }

    @Override
    public RankResource toResource(final Rank rank) {
        return new RankResource(rank);
    }
}
