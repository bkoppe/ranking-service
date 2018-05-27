package de.augsburg1871.handball.ranking.api.v1.model;

import com.google.common.collect.Lists;
import de.augsburg1871.handball.ranking.api.v1.RankingController;
import de.augsburg1871.handball.ranking.persistence.model.Note;
import de.augsburg1871.handball.ranking.persistence.model.Rank;
import de.augsburg1871.handball.ranking.persistence.model.Ranking;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class RankingResource extends ResourceSupport {

    private final Ranking ranking;

    public RankingResource(Ranking ranking) {
        this.ranking = ranking;
        this.add(linkTo(methodOn(RankingController.class).getRanking(ranking.getId().getSeason().getId(), ranking.getId().getAgeGroup().getShortcode())).withSelfRel());
    }

    public String getDescription() {
        return ranking.getId().getSeason() + " - " + ranking.getId().getAgeGroup();
    }

    public List<RankResource> getRanks(){
        return Lists.newArrayList(ranking.getRanks().stream().map(RankResource::new).collect(Collectors.toList()));
    }

    public List<NoteResource> getNotes(){
        return Lists.newArrayList(ranking.getNotes().stream().map(NoteResource::new).collect(Collectors.toList()));
    }

}
