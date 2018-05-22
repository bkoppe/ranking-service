package de.augsburg1871.handball.ranking;

import de.augsburg1871.handball.ranking.persistence.RankingRepository;
import de.augsburg1871.handball.ranking.persistence.model.Rank;
import de.augsburg1871.handball.ranking.persistence.model.Ranking;
import de.augsburg1871.handball.ranking.persistence.model.embeddable.RankingId;
import de.augsburg1871.handball.ranking.service.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

import static de.augsburg1871.handball.ranking.persistence.model.AgeGroup.MAENNER_2;
import static de.augsburg1871.handball.ranking.persistence.model.Season.SEASON_2017_2018;

@SpringBootApplication
public class RankingServiceApplication implements CommandLineRunner {

    private static final String TABELLE = "Tabelle";

    @Autowired
    private RankingRepository rankRepository;

    @Autowired
    private RankingService rankingService;

    public static void main(String[] args) {
        SpringApplication.run(RankingServiceApplication.class, args);
    }

    @Override
    public void run(final String... args) throws Exception {
//        rankRepository.deleteAll();

        Ranking.Builder builder = new Ranking.Builder(SEASON_2017_2018, MAENNER_2);

        Ranking ranking = builder
                .withUrl("https://bhv-handball.liga.nu/cgi-bin/WebObjects/nuLigaHBDE.woa/wa/groupPage?championship=SW+2017%2F18&group=215160")
//                .withRanks(new Rank.Builder(rankingId, "Augsburg 1871").build())
                .withParsingEnabled(true)
                .build();

        rankRepository.save(ranking);

        ranking = rankingService.getRankingFor(MAENNER_2);

        List<Ranking> rankings = rankRepository.findAll();
        for (Ranking r : rankings) {
            System.out.println(r);
            for (Rank rank : r.getRanks()) {
                System.out.println(rank);
            }
        }

        System.out.println("++done++");
    }
}
