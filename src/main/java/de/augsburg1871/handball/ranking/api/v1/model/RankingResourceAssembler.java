package de.augsburg1871.handball.ranking.api.v1.model;

import de.augsburg1871.handball.ranking.api.v1.RankingController;
import de.augsburg1871.handball.ranking.persistence.model.Ranking;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class RankingResourceAssembler extends ResourceAssemblerSupport<Ranking, RankingResource> {

    public RankingResourceAssembler() {
        super(RankingController.class, RankingResource.class);
    }

    @Override
    public RankingResource toResource(final Ranking entity) {
        return new RankingResource(entity);
    }

}
