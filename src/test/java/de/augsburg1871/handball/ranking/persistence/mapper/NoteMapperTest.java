package de.augsburg1871.handball.ranking.persistence.mapper;

import de.augsburg1871.handball.ranking.parser.model.NoteTO;
import de.augsburg1871.handball.ranking.parser.model.RankingTO;
import de.augsburg1871.handball.ranking.persistence.model.Note;
import de.augsburg1871.handball.ranking.persistence.model.Rank;
import de.augsburg1871.handball.ranking.persistence.model.Ranking;
import org.junit.Test;

import static de.augsburg1871.handball.ranking.persistence.model.AgeGroup.MAENNER_2;
import static de.augsburg1871.handball.ranking.persistence.model.Season.SEASON_2017_2018;
import static org.assertj.core.api.Assertions.assertThat;

public class NoteMapperTest {

    @Test
    public void map() {
        Ranking ranking = new Ranking(SEASON_2017_2018, MAENNER_2);
        ranking.add(new Rank());

        NoteTO noteTO = new NoteTO();
        noteTO.setRang(11);
        noteTO.setMannschaft("SV Mering II");
        noteTO.setHinweis("zurückgezogen am 10.11.2017");
        RankingTO rankingTO = new RankingTO();
        rankingTO.addNote(noteTO);

        NoteMapper.map(ranking, rankingTO.getNoteTOs());

        // Don't touch existing ranks
        assertThat(ranking.getRanks()).hasSize(1);

        // Verify fileds
        Note firstNote = ranking.getNotes().iterator().next();
        assertThat(firstNote.getId()).isNotNull();
        assertThat(firstNote.getTeam()).isEqualTo("SV Mering II");
        assertThat(firstNote.getNote()).isEqualTo("zurückgezogen am 10.11.2017");
    }

}