package de.augsburg1871.handball.ranking.api.v1;

import com.google.common.collect.Lists;
import de.augsburg1871.handball.ranking.RankingProperties;
import de.augsburg1871.handball.ranking.api.v1.model.RankResource;
import de.augsburg1871.handball.ranking.api.v1.model.RankingResource;
import de.augsburg1871.handball.ranking.api.v1.model.RankingResourceAssembler;
import de.augsburg1871.handball.ranking.persistence.model.AgeGroup;
import de.augsburg1871.handball.ranking.persistence.model.Ranking;
import de.augsburg1871.handball.ranking.persistence.model.Season;
import de.augsburg1871.handball.ranking.service.RankingService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@EnableConfigurationProperties(RankingProperties.class)
@RestController
@RequestMapping(path = "/api/v1/tabellen", produces = APPLICATION_JSON_VALUE)
public class RankingController {

    private final RankingProperties properties;
    private final RankingResourceAssembler resourceAssembler = new RankingResourceAssembler();
    private final RankingService rankingService;

    public RankingController(final RankingService rankingService, final RankingProperties rankingProperties) {
        this.rankingService = rankingService;
        properties = rankingProperties;
    }


    @GetMapping(path = "{season}")
    public Resources<RankingResource> getRankings(@PathVariable String season) {
        Season mappedSeason;
        if ("current".equalsIgnoreCase(season)) {
            mappedSeason = properties.getCurrentSeason();
        } else {
            mappedSeason = Season.getById(season);
        }

        List<Ranking> rankings = rankingService.getRankings(mappedSeason);
        return new Resources<RankingResource>(rankings.stream()
                .map(resourceAssembler::toResource)
                .collect(Collectors.toList()));
    }

    @GetMapping(path = "{season}/{age-group}")
    public RankingResource getRanking(@PathVariable String season, @PathVariable(name = "age-group") String ageGroup) {
        AgeGroup mappedAgeGroup = AgeGroup.getByShortcode(ageGroup);

        Season mappedSeason;
        if ("current".equalsIgnoreCase(season)) {
            mappedSeason = properties.getCurrentSeason();
        } else {
            mappedSeason = Season.getById(season);
        }

        Ranking ranking = rankingService.getRankingFor(mappedSeason, mappedAgeGroup);
        return resourceAssembler.toResource(ranking);
    }

}
